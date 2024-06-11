package webly.meyzieu_gym.back.registrationmanagement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/registrations")
@PreAuthorize("hasRole('GUARDIAN')")
public class RegistrationController {
    
    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }
    @PreAuthorize("@registrationService.isMemberOwner(#registerMemberDto.memberId, authentication.principal.id)")
    @PostMapping
    public ResponseEntity<Void> registerMember(@Valid @RequestBody RegisterMemberDto registerMemberDto) {
        registrationService.registerMember(registerMemberDto);
        return ResponseEntity.ok().build();
    }
}
