package webly.meyzieu_gym.back.emailmanagement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailConfService {
    
    private JavaMailSender mailSender;

    public EmailConfService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Value("${meyzieu-gym.app.email}")
    private String email;

    public void sendEmailForSignup(String newUserEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email);
        message.setTo(newUserEmail);
        message.setSubject("Bienvenue chez Meyzieu Gym !");
        message.setText("Bonjour,\n\nNous sommes ravis de vous accueillir chez Meyzieu Gym !" + 
        "\n\nVotre compte a bien été créé !" +
        "\n\nVous pouvez dès à présent vous connecter sur le site et inscrire vos enfants à un cours pour la saison à venir." +
        "Si vous avez des questions ou besoin d'assistance, n'hésitez pas à nous contacter.\n\nÀ très bientôt sur notre plateforme."+
        "\n\nSportivement,\nL'équipe Meyzieu Gym");
        mailSender.send(message);
    }

    public void sendEmailForRegister(EmailRequest emailRequest) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email);
        message.setTo(emailRequest.getTo());
        message.setSubject(emailRequest.getSubject());
        message.setText(emailRequest.getMessage());
        mailSender.send(message);
    }
}
