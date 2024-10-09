package webly.meyzieu_gym.back.security.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.stereotype.Service;
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

    public void initiatePasswordReset(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Adresse e-mail non trouvée"));

        // Créer un token JWT ou un token aléatoire
        String token = UUID.randomUUID().toString();

        // Stocker le token avec une expiration de 60 minutes
        PasswordResetToken resetToken = new PasswordResetToken(token, user, 60);
        passwordResetTokenRepository.save(resetToken);

        // Envoyer un email avec un lien de réinitialisation
        String resetUrl = "www.meyzieu-gym.fr/reset-password?token=" + token;
        emailConfService.sendPasswordResetEmail(user.getEmail(), resetUrl);
    }

    public boolean isTokenValid(String token) {
        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(token)
                .orElseThrow(() -> new InvalidTokenException("Token invalide"));

        return !resetToken.getExpirationDate().isBefore(LocalDateTime.now());
    }

    public void resetPassword(String token, String newPassword) {
        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(token)
                .orElseThrow(() -> new InvalidTokenException("Token invalide"));

        if (resetToken.getExpirationDate().isBefore(LocalDateTime.now())) {
            throw new TokenExpiredException("Token expiré");
        }

        // Réinitialiser le mot de passe de l'utilisateur
        User user = resetToken.getUser();
        user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        userRepository.save(user);
    }
}
