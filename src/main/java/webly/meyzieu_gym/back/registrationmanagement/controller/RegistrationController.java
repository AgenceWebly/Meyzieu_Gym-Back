package webly.meyzieu_gym.back.registrationmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import webly.meyzieu_gym.back.registrationmanagement.dto.NewRegistrationDto;
import webly.meyzieu_gym.back.registrationmanagement.dto.UpdateHealthCertificateDto;
import webly.meyzieu_gym.back.registrationmanagement.service.RegistrationService;

@RestController
@RequestMapping("/api/registrations")
@PreAuthorize("hasRole('GUARDIAN')")
public class RegistrationController {
    
    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PreAuthorize("@registrationService.isMemberOwner(#newRegistrationDto.memberId, authentication.principal.id)")
    @PostMapping
    public ResponseEntity<Void> registerMember(@Valid @RequestBody NewRegistrationDto newRegistrationDto) {
        registrationService.registerMember(newRegistrationDto);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("@registrationService.isRegistrationOwner(#updateHealthCertificateDto.id, authentication.principal.id)")
    @PutMapping("/health-certificate")
    public ResponseEntity<Void> updateHealthCertificate(@Valid @RequestBody UpdateHealthCertificateDto updateHealthCertificateDto) {
        registrationService.updateHealthCertificate(updateHealthCertificateDto);
        return ResponseEntity.ok().build();
    }
}
