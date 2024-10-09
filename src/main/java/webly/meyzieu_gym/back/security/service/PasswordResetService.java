package webly.meyzieu_gym.back.security.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webly.meyzieu_gym.back.common.exception.custom.InvalidTokenException;
import webly.meyzieu_gym.back.common.exception.custom.TokenExpiredException;
import webly.meyzieu_gym.back.emailmanagement.EmailConfService;
import webly.meyzieu_gym.back.usermanagement.user.entity.PasswordResetToken;
import webly.meyzieu_gym.back.usermanagement.user.entity.User;
import webly.meyzieu_gym.back.usermanagement.user.repository.PasswordResetTokenRepository;
import webly.meyzieu_gym.back.usermanagement.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PasswordResetService {

    private final UserRepository userRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final EmailConfService emailConfService;

    public PasswordResetService(UserRepository userRepository, PasswordResetTokenRepository passwordResetTokenRepository, EmailConfService emailConfService) {
        this.userRepository = userRepository;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.emailConfService = emailConfService;
    }

    @Transactional
    public void initiatePasswordReset(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Adresse e-mail non trouvée"));

        // Supprimer tous les anciens tokens de l'utilisateur avant d'en générer un nouveau
        passwordResetTokenRepository.deleteByUser(user);

        // Créer un token JWT ou un token aléatoire
        String token = UUID.randomUUID().toString();

        // Hasher le token avant sauvegarde en base de données
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedToken = passwordEncoder.encode(token);

        // Stocker le token hashé avec une expiration de 60 minutes
        PasswordResetToken resetToken = new PasswordResetToken(hashedToken, user, 60);
        passwordResetTokenRepository.save(resetToken);

        // Envoyer un email avec un lien de réinitialisation
        String resetUrl = "https://www.meyzieu-gym.fr/reinitialisation-mot-de-passe?token=" + token + "&email=" + email;
        emailConfService.sendPasswordResetEmail(user.getEmail(), resetUrl);
    }

    public void resetPassword(String token, String newPassword, String email) {
        // Rechercher l'utilisateur par son email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));

        // Récupérer le token haché de réinitialisation associé à l'utilisateur
        PasswordResetToken resetToken = passwordResetTokenRepository.findByUser(user)
                .orElseThrow(() -> new InvalidTokenException("Token non trouvé pour cet utilisateur"));

        // Vérifier la validité du token (expiration)
        if (resetToken.getExpirationDate().isBefore(LocalDateTime.now())) {
            throw new TokenExpiredException("Le token a expiré");
        }

        // Vérifier la concordance du token reçu avec le token haché stocké
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(token, resetToken.getToken())) {
            throw new InvalidTokenException("Le token fourni est invalide");
        }

        // Réinitialiser le mot de passe de l'utilisateur
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        // Supprimer le token de réinitialisation après utilisation
        passwordResetTokenRepository.delete(resetToken);
    }
}
