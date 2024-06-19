package webly.meyzieu_gym.back.coursemanagement.service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import webly.meyzieu_gym.back.coursemanagement.dto.CourseDto;
import webly.meyzieu_gym.back.coursemanagement.dto.ProgramDto;
import webly.meyzieu_gym.back.coursemanagement.dto.SeasonDto;
import webly.meyzieu_gym.back.coursemanagement.dto.TrainingSlotDto;
import webly.meyzieu_gym.back.coursemanagement.entity.Course;
import webly.meyzieu_gym.back.coursemanagement.entity.Program;
import webly.meyzieu_gym.back.coursemanagement.entity.Season;
import webly.meyzieu_gym.back.coursemanagement.entity.TrainingSlot;
import webly.meyzieu_gym.back.coursemanagement.repository.CourseRepository;
import webly.meyzieu_gym.back.membermanagement.entity.Member;
import webly.meyzieu_gym.back.membermanagement.repository.MemberRepository;
import webly.meyzieu_gym.back.registrationmanagement.repository.RegistrationRepository;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final RegistrationRepository registrationRepository;
    private final MemberRepository memberRepository;

    public CourseService(CourseRepository courseRepository, RegistrationRepository registrationRepository, MemberRepository memberRepository) {
        this.courseRepository = courseRepository;
        this.registrationRepository = registrationRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional(readOnly = true)
    public List<CourseDto> getAvailableCoursesForMemberId(Long memberId, Long userId) {
        LocalDateTime now = LocalDateTime.now();
        Date currentDate = new Date();

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        return courseRepository.findAll().stream()
                .filter(course -> isCourseAvailableForMember(course, member, now, currentDate))
                .map(course ->mapToCourseDto(course, userId))
                .collect(Collectors.toList());
    }

    // Checks if a course is available for a member based on various conditions
    private boolean isCourseAvailableForMember(Course course, Member member, LocalDateTime now, Date currentDate) {

        return isRegistrationPeriodOpen(course, now, currentDate) &&
               isMemberNotRegisteredForSeason(course, member) &&
               isAgeValidForCourse(course, member);
    }

    // Checks if the registration period for a course is open
    private boolean isRegistrationPeriodOpen(Course course, LocalDateTime now, Date currentDate) {
        return course.getRegistrationEndDate().isAfter(now) && course.getSeason().getEndDate().after(currentDate);
    }

    // Checks if a member is not registered for the season of the course
    private boolean isMemberNotRegisteredForSeason(Course course, Member member) {
        return !registrationRepository.existsByMemberIdAndCourseSeasonId(member.getId(), course.getSeason().getId());
    }

    // Checks if a member's age is valid for the course based on the course's age requirements.
    private boolean isAgeValidForCourse(Course course, Member member) {
        Date memberBirthDate = member.getBirthdate();

        if (memberBirthDate == null) {
            return false;
        }

        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);

        // Calculate min birth date (January 1 of the year currentYear - maxAge)
        calendar.set(currentYear - course.getMaxAge(), Calendar.JANUARY, 1);
        Date minBirthDate = calendar.getTime();

        // Calculate max birth date (December 31 of the year currentYear - minAge)
        calendar.set(currentYear - course.getMinAge(), Calendar.DECEMBER, 31);
        Date maxBirthDate = calendar.getTime();

        return (memberBirthDate.after(minBirthDate) || memberBirthDate.equals(minBirthDate)) &&
               (memberBirthDate.before(maxBirthDate) || memberBirthDate.equals(maxBirthDate));
    }

    private CourseDto mapToCourseDto(Course course, Long userId) {
        long registrationsCount = registrationRepository.countByCourseId(course.getId());
        int remainingSlots = course.getMaxMembers() - (int) registrationsCount;

        SeasonDto seasonDto = mapToSeasonDto(course.getSeason());
        ProgramDto programDto = mapToProgramDto(course.getProgram());
        List<TrainingSlotDto> trainingSlotDtos = mapToTrainingSlotDtos(course.getTrainingSlots());

        // Count the number of registrations for the user for the season of this course
        int userRegistrationsCount = (int) registrationRepository.countByUserIdAndSeasonId(userId, course.getSeason().getId());

        return new CourseDto(
                course.getId(),
                seasonDto,
                programDto,
                course.getRegistrationStartDate(),
                course.getRegistrationEndDate(),
                course.getPrice(),
                course.getMaxMembers(),
                course.getMinAge(),
                course.getMaxAge(),
                trainingSlotDtos,
                remainingSlots,
                userRegistrationsCount
        );
    }

    private SeasonDto mapToSeasonDto(Season season) {
        return new SeasonDto(
            season.getId(),
            season.getStartDate(),
            season.getEndDate());
    }

    private ProgramDto mapToProgramDto(Program program) {
        return new ProgramDto(
            program.getId(), 
            program.getName(), 
            program.getDescription(), 
            program.isIncludingCompetition());
    }

    private List<TrainingSlotDto> mapToTrainingSlotDtos(List<TrainingSlot> trainingSlots) {
        return trainingSlots.stream()
                .map(slot -> new TrainingSlotDto(
                        slot.getId(),
                        slot.getDay(),
                        slot.getStartTime(),
                        slot.getEndTime()))
                .collect(Collectors.toList());
    }
}
