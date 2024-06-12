package webly.meyzieu_gym.back.registrationmanagement.service;

import org.springframework.stereotype.Service;

import webly.meyzieu_gym.back.registrationmanagement.repository.RegistrationRepository;

@Service
public class RegistrationOwnershipService {

    private final RegistrationRepository registrationRepository;

    public RegistrationOwnershipService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public boolean isRegistrationOwner(Long registrationId, Long userId) {
        return registrationRepository.existsByIdAndMemberGuardianUserId(registrationId, userId);
    }
}
