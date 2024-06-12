package webly.meyzieu_gym.back.registrationmanagement.service;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import webly.meyzieu_gym.back.common.exception.custom.CourseNotFoundException;
import webly.meyzieu_gym.back.common.exception.custom.DuplicateRegistrationException;
import webly.meyzieu_gym.back.common.exception.custom.MemberNotFoundException;
import webly.meyzieu_gym.back.common.exception.custom.RegistrationNotFoundException;
import webly.meyzieu_gym.back.membermanagement.entity.Member;
import webly.meyzieu_gym.back.membermanagement.repository.MemberGuardianRepository;
import webly.meyzieu_gym.back.membermanagement.repository.MemberRepository;
import webly.meyzieu_gym.back.programmanagement.entity.Course;
import webly.meyzieu_gym.back.programmanagement.repository.CourseRepository;
import webly.meyzieu_gym.back.registrationmanagement.dto.NewRegistrationDto;
import webly.meyzieu_gym.back.registrationmanagement.dto.UpdateHealthCertificateDto;
import webly.meyzieu_gym.back.registrationmanagement.entity.Registration;
import webly.meyzieu_gym.back.registrationmanagement.repository.RegistrationRepository;

@Service
public class RegistrationService {
    
    private final MemberRepository memberRepository;
    private final CourseRepository courseRepository;
    private final RegistrationRepository registrationRepository;
    private final MemberGuardianRepository memberGuardianRepository;

    public RegistrationService(MemberRepository memberRepository, CourseRepository courseRepository, RegistrationRepository registrationRepository, MemberGuardianRepository memberGuardianRepository) {
        this.memberRepository = memberRepository;
        this.courseRepository = courseRepository;
        this.registrationRepository = registrationRepository;
        this.memberGuardianRepository = memberGuardianRepository;
    }

    public boolean isMemberOwner(Long memberId, Long userId) {
        return memberGuardianRepository.findByMemberIdAndUserId(memberId, userId).isPresent();
    }
    
    public boolean isRegistrationOwner(Long registrationId, Long userId) {
        return registrationRepository.existsByIdAndMemberGuardianUserId(registrationId, userId);
    }

    @Transactional
    public void registerMember(NewRegistrationDto newRegistrationDto){
        Member member = memberRepository.findById(newRegistrationDto.getMemberId())
                .orElseThrow(() -> new MemberNotFoundException("Member not found"));
        Course course = courseRepository.findById(newRegistrationDto.getCourseId())
                .orElseThrow(() -> new CourseNotFoundException("Course not found"));

        boolean isAlreadyRegistered = registrationRepository.existsByMemberIdAndCourseSeasonId(
                newRegistrationDto.getMemberId(), course.getSeason().getId());
        
        if (isAlreadyRegistered) {
            throw new DuplicateRegistrationException("Member is already registered for a course in this season");
        }

        Registration registration = new Registration(
            member,
            course,
            newRegistrationDto.getRegistrationFee(),
            newRegistrationDto.getPaymentMethod(),
            newRegistrationDto.getPaymentStatus(),
            newRegistrationDto.getRegistrationStatus(),
            newRegistrationDto.getRegistrationDate(),
            null,
            false
        );

        registrationRepository.save(registration);
    }

    @Transactional
    public void updateHealthCertificate(UpdateHealthCertificateDto updateHealthCertificateDto) {
        Registration registration = registrationRepository.findById(updateHealthCertificateDto.getId())
                .orElseThrow(() -> new RegistrationNotFoundException("Registration not found"));

        registration.setHealthCertificateRequired(updateHealthCertificateDto.isHealthCertificateRequired());
        registration.setHealthCertificateFileUrl(updateHealthCertificateDto.getHealthCertificateFileUrl());
        registrationRepository.save(registration);
    }
}
