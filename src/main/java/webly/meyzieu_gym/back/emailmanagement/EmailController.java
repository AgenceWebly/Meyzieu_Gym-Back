package webly.meyzieu_gym.back.emailmanagement;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class EmailController {
    
    private EmailConfService emailConfService;


    public EmailController(EmailConfService emailConfService) {
        this.emailConfService = emailConfService;
    }

    @PostMapping("/api/send-email")
    public void sendEmail(@RequestBody EmailRequest emailRequest) {
        emailConfService.sendEmailForRegister(emailRequest);
    }
    
}
