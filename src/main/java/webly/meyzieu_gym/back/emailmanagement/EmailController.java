package webly.meyzieu_gym.back.emailmanagement;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class EmailController {
    
    private EmailConfService emailConfService;


    public EmailController(EmailConfService emailConfService) {
        this.emailConfService = emailConfService;
    }

    @PostMapping("/api/send-email")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> sendEmail(@RequestBody EmailRequest emailRequest) {
        emailConfService.sendEmailForRegister(emailRequest);
        return ResponseEntity.ok().build();
    }
}
