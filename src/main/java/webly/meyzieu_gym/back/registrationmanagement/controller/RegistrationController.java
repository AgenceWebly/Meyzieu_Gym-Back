package webly.meyzieu_gym.back.registrationmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import webly.meyzieu_gym.back.registrationmanagement.dto.NewRegistrationDto;
import webly.meyzieu_gym.back.registrationmanagement.dto.UpdateRegistrationDto;
import webly.meyzieu_gym.back.registrationmanagement.service.RegistrationService;

@RestController
@RequestMapping("/api/registrations")
@PreAuthorize("hasRole('GUARDIAN')")
public class RegistrationController {
    
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PreAuthorize("@memberOwnershipService.isMemberOwner(#newRegistrationDto.memberId, authentication.principal.id)")
    @PostMapping
    public ResponseEntity<Long> registerMember(@Valid @RequestBody NewRegistrationDto newRegistrationDto) {
        Long registrationId = registrationService.registerMember(newRegistrationDto);
        return ResponseEntity.ok(registrationId);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@registrationOwnershipService.isRegistrationOwner(#id, authentication.principal.id)")
    public ResponseEntity<Void> updateRegistration(@PathVariable Long id, @Valid @RequestBody UpdateRegistrationDto updateRegistrationDto) {
        registrationService.updateRegistration(id, updateRegistrationDto);
        return ResponseEntity.ok().build();
    }
}
