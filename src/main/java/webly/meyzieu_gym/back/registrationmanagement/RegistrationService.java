package webly.meyzieu_gym.back.registrationmanagement;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import webly.meyzieu_gym.back.common.exception.custom.CourseNotFoundException;
import webly.meyzieu_gym.back.common.exception.custom.DuplicateRegistrationException;
import webly.meyzieu_gym.back.common.exception.custom.MemberNotFoundException;
import webly.meyzieu_gym.back.membermanagement.entity.Member;
import webly.meyzieu_gym.back.membermanagement.repository.MemberGuardianRepository;
import webly.meyzieu_gym.back.membermanagement.repository.MemberRepository;
import webly.meyzieu_gym.back.programmanagement.entity.Course;
import webly.meyzieu_gym.back.programmanagement.repository.CourseRepository;

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
    
    @Transactional
    public void registerMember(RegisterMemberDto registerMemberDto){
        Member member = memberRepository.findById(registerMemberDto.getMemberId())
                .orElseThrow(() -> new MemberNotFoundException("Member not found"));
        Course course = courseRepository.findById(registerMemberDto.getCourseId())
                .orElseThrow(() -> new CourseNotFoundException("Course not found"));

        boolean isAlreadyRegistered = registrationRepository.existsByMemberIdAndCourseSeasonId(
                registerMemberDto.getMemberId(), course.getSeason().getId());
        
        if (isAlreadyRegistered) {
            throw new DuplicateRegistrationException("Member is already registered for a course in this season");
        }

        Registration registration = new Registration(
            member,
            course,
            registerMemberDto.getRegistrationFee(),
            registerMemberDto.getPaymentMethod(),
            registerMemberDto.getPaymentStatus(),
            registerMemberDto.getRegistrationStatus(),
            registerMemberDto.getRegistrationDate(),
            registerMemberDto.getHealthCertificateFileUrl(),
            registerMemberDto.isHealthCertificateRequired()
        );
        
        registrationRepository.save(registration);
    }
}
