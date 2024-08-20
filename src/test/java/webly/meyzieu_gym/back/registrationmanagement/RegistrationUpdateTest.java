package webly.meyzieu_gym.back.registrationmanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import webly.meyzieu_gym.back.common.exception.custom.RegistrationNotFoundException;
import webly.meyzieu_gym.back.coursemanagement.repository.CourseRepository;
import webly.meyzieu_gym.back.membermanagement.repository.MemberRepository;
import webly.meyzieu_gym.back.registrationmanagement.dto.UpdateRegistrationDto;
import webly.meyzieu_gym.back.registrationmanagement.entity.Registration;
import webly.meyzieu_gym.back.registrationmanagement.repository.RegistrationRepository;
import webly.meyzieu_gym.back.registrationmanagement.service.RegistrationService;

class RegistrationUpdateTest {
    
    @Mock
    private MemberRepository memberRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private RegistrationRepository registrationRepository;

    @InjectMocks
    private RegistrationService registrationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void updateRegistration_ShouldUpdateSuccessfully_WhenValidDataIsProvided() {
        Long registrationId = 1L;
        UpdateRegistrationDto updateDto = new UpdateRegistrationDto();
        updateDto.setIsHealthCertificateRequired(Optional.of(true));
        updateDto.setHealthCertificateFileUrl(Optional.of("new-file-url"));
        updateDto.setPaymentMethod(Optional.of("Credit Card"));
        updateDto.setPaymentStatus(Optional.of("Paid"));
        updateDto.setRegistrationStatus(Optional.of("Confirmed"));

        Registration existingRegistration = new Registration();
        existingRegistration.setId(registrationId);

        when(registrationRepository.findById(registrationId)).thenReturn(Optional.of(existingRegistration));

        registrationService.updateRegistration(registrationId, updateDto);

        verify(registrationRepository, times(1)).save(existingRegistration);
        assertEquals(true, existingRegistration.isHealthCertificateRequired());
        assertEquals("new-file-url", existingRegistration.getHealthCertificateFileUrl());
        assertEquals("Credit Card", existingRegistration.getPaymentMethod());
        assertEquals("Paid", existingRegistration.getPaymentStatus());
        assertEquals("Confirmed", existingRegistration.getRegistrationStatus());
    }

    @Test
    void updateRegistration_ShouldThrowRegistrationNotFoundException_WhenRegistrationDoesNotExist() {
        Long registrationId = 1L;
        UpdateRegistrationDto updateDto = new UpdateRegistrationDto();

        when(registrationRepository.findById(registrationId)).thenReturn(Optional.empty());

        assertThrows(RegistrationNotFoundException.class, () -> 
            registrationService.updateRegistration(registrationId, updateDto)
        );

        verify(registrationRepository, never()).save(any());
    }

    @Test
    void updateRegistration_ShouldOnlyUpdateProvidedFields_WhenPartialDataIsProvided() {
        Long registrationId = 1L;
        UpdateRegistrationDto updateDto = new UpdateRegistrationDto();
        updateDto.setPaymentMethod(Optional.of("Credit Card")); // Only updating payment method

        Registration existingRegistration = new Registration();
        existingRegistration.setId(registrationId);
        existingRegistration.setPaymentStatus("Pending");

        when(registrationRepository.findById(registrationId)).thenReturn(Optional.of(existingRegistration));

        registrationService.updateRegistration(registrationId, updateDto);

        verify(registrationRepository, times(1)).save(existingRegistration);
        assertEquals("Credit Card", existingRegistration.getPaymentMethod());
        assertEquals("Pending", existingRegistration.getPaymentStatus()); // Unchanged
    }

    @Test
    void updateRegistration_ShouldMakeNoChanges_WhenNoFieldsAreProvided() {
        Long registrationId = 1L;
        UpdateRegistrationDto updateDto = new UpdateRegistrationDto(); // No fields set

        Registration existingRegistration = new Registration();
        existingRegistration.setId(registrationId);
        existingRegistration.setPaymentStatus("Pending");

        when(registrationRepository.findById(registrationId)).thenReturn(Optional.of(existingRegistration));

        registrationService.updateRegistration(registrationId, updateDto);

        verify(registrationRepository, times(1)).save(existingRegistration);
        assertEquals("Pending", existingRegistration.getPaymentStatus()); // Unchanged
    }
}
