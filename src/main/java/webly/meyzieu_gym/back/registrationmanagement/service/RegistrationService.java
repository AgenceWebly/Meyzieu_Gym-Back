package webly.meyzieu_gym.back.registrationmanagement.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import webly.meyzieu_gym.back.common.exception.custom.CourseNotFoundException;
import webly.meyzieu_gym.back.common.exception.custom.DuplicateRegistrationException;
import webly.meyzieu_gym.back.common.exception.custom.MemberNotFoundException;
import webly.meyzieu_gym.back.common.exception.custom.RegistrationAvailabilityException;
import webly.meyzieu_gym.back.common.exception.custom.RegistrationNotFoundException;
import webly.meyzieu_gym.back.membermanagement.entity.Member;
import webly.meyzieu_gym.back.membermanagement.repository.MemberRepository;
import webly.meyzieu_gym.back.coursemanagement.dto.TrainingSlotDto;
import webly.meyzieu_gym.back.coursemanagement.entity.Course;
import webly.meyzieu_gym.back.coursemanagement.repository.CourseRepository;
import webly.meyzieu_gym.back.registrationmanagement.dto.NewRegistrationDto;
import webly.meyzieu_gym.back.registrationmanagement.dto.RegistrationDetailsDto;
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
        try {
            Member member = memberRepository.findById(newRegistrationDto.getMemberId())
                    .orElseThrow(() -> new MemberNotFoundException("L'adhérent n'a pas été trouvé"));
            Course course = courseRepository.findById(newRegistrationDto.getCourseId())
                    .orElseThrow(() -> new CourseNotFoundException("Le cours n'a pas été trouvé"));

            int registrationsCount = registrationRepository.countByCourseId(course.getId());
            if ((course.getMaxMembers() - registrationsCount) <= 0) {
                throw new RegistrationAvailabilityException("Le cours est complet");
            }

            boolean isAlreadyRegistered = registrationRepository.existsByMemberIdAndCourseSeasonId(
                    newRegistrationDto.getMemberId(), course.getSeason().getId());
            if (isAlreadyRegistered) {
                throw new DuplicateRegistrationException("L'adhérent est déjà inscrit à un cours de la même saison");
            }
            
            String stage = "En attente";
            Registration registration = new Registration(
                member,
                course,
                newRegistrationDto.getRegistrationFee(),
                stage,
                stage,
                newRegistrationDto.getRegistrationStatus(),
                LocalDateTime.now(),
                null,
                false
            );

            Registration savedRegistration = registrationRepository.save(registration);
            return savedRegistration.getId();
        } catch (DataIntegrityViolationException e) {
            throw new RegistrationAvailabilityException("Le cours est complet");
        }
    }

    @Transactional
    public void updateRegistration(Long id, UpdateRegistrationDto updatedRegistrationDto) {
        Registration registration = registrationRepository.findById(id)
                .orElseThrow(() -> new RegistrationNotFoundException("L'inscription n'a pas été trouvée"));

        updatedRegistrationDto.getIsHealthCertificateRequired().ifPresent(registration::setHealthCertificateRequired);
        updatedRegistrationDto.getHealthCertificateFileUrl().ifPresent(registration::setHealthCertificateFileUrl);
        updatedRegistrationDto.getPaymentMethod().ifPresent(registration::setPaymentMethod);
        updatedRegistrationDto.getPaymentStatus().ifPresent(registration::setPaymentStatus);
        updatedRegistrationDto.getRegistrationStatus().ifPresent(registration::setRegistrationStatus);
        registrationRepository.save(registration);
    }

    @Transactional(readOnly = true)
    public RegistrationDetailsDto getRegistrationById(Long registrationId) {
        Registration registration = registrationRepository.findById(registrationId)
                .orElseThrow(() -> new RegistrationNotFoundException("L'inscription n'a pas été trouvée"));

        Member member = registration.getMember();
        Course course = registration.getCourse();

        List<TrainingSlotDto> trainingSlotDtos = course.getTrainingSlots().stream()
                .map(slot -> new TrainingSlotDto(slot.getId(), slot.getDay(), slot.getStartTime(), slot.getEndTime()))
                .collect(Collectors.toList());

        return new RegistrationDetailsDto(
                member.getFirstname(),
                member.getLastname(),
                member.getBirthdate(),
                course.getCourseName(),
                trainingSlotDtos,
                registration.getRegistrationFee()
        );
    }
}
