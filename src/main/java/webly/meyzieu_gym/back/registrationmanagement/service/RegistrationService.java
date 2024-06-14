package webly.meyzieu_gym.back.registrationmanagement.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import webly.meyzieu_gym.back.common.exception.custom.CourseNotFoundException;
import webly.meyzieu_gym.back.common.exception.custom.DuplicateRegistrationException;
import webly.meyzieu_gym.back.common.exception.custom.MemberNotFoundException;
import webly.meyzieu_gym.back.common.exception.custom.RegistrationNotFoundException;
import webly.meyzieu_gym.back.membermanagement.entity.Member;
import webly.meyzieu_gym.back.membermanagement.repository.MemberRepository;
import webly.meyzieu_gym.back.coursemanagement.entity.Course;
import webly.meyzieu_gym.back.coursemanagement.repository.CourseRepository;
import webly.meyzieu_gym.back.registrationmanagement.dto.NewRegistrationDto;
import webly.meyzieu_gym.back.registrationmanagement.dto.UpdateRegistrationDto;
import webly.meyzieu_gym.back.registrationmanagement.entity.Registration;
import webly.meyzieu_gym.back.registrationmanagement.repository.RegistrationRepository;

@Service
public class RegistrationService {
    
    private final MemberRepository memberRepository;
    private final CourseRepository courseRepository;
    private final RegistrationRepository registrationRepository;

    public RegistrationService(MemberRepository memberRepository, CourseRepository courseRepository, RegistrationRepository registrationRepository) {
        this.memberRepository = memberRepository;
        this.courseRepository = courseRepository;
        this.registrationRepository = registrationRepository;
    }

    @Transactional
    public Long registerMember(NewRegistrationDto newRegistrationDto){
        Member member = memberRepository.findById(newRegistrationDto.getMemberId())
                .orElseThrow(() -> new MemberNotFoundException("Member not found"));
        Course course = courseRepository.findById(newRegistrationDto.getCourseId())
                .orElseThrow(() -> new CourseNotFoundException("Course not found"));

        boolean isAlreadyRegistered = registrationRepository.existsByMemberIdAndCourseSeasonId(
                newRegistrationDto.getMemberId(), course.getSeason().getId());
        
        if (isAlreadyRegistered) {
            throw new DuplicateRegistrationException("Member is already registered for a course in this season");
        }
        
        String stage = "En attente";
        Registration registration = new Registration(
            member,
            course,
            newRegistrationDto.getRegistrationFee(),
            stage,
            stage,
            stage,
            LocalDateTime.now(),
            null,
            false
        );

        Registration savedRegistration = registrationRepository.save(registration);
        return savedRegistration.getId();
    }

    @Transactional
    public void updateRegistration(Long id, UpdateRegistrationDto updatedRegistrationDto) {
        Registration registration = registrationRepository.findById(id)
                .orElseThrow(() -> new RegistrationNotFoundException("Registration not found"));

        updatedRegistrationDto.getIsHealthCertificateRequired().ifPresent(registration::setHealthCertificateRequired);
        updatedRegistrationDto.getHealthCertificateFileUrl().ifPresent(registration::setHealthCertificateFileUrl);
        updatedRegistrationDto.getPaymentMethod().ifPresent(registration::setPaymentMethod);
        updatedRegistrationDto.getPaymentStatus().ifPresent(registration::setPaymentStatus);
        updatedRegistrationDto.getRegistrationStatus().ifPresent(registration::setRegistrationStatus);
        registrationRepository.save(registration);
    }
}
