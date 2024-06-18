package webly.meyzieu_gym.back.coursemanagement.service;

import java.time.LocalDateTime;
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
import webly.meyzieu_gym.back.registrationmanagement.repository.RegistrationRepository;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final RegistrationRepository registrationRepository;

    public CourseService(CourseRepository courseRepository, RegistrationRepository registrationRepository) {
        this.courseRepository = courseRepository;
        this.registrationRepository = registrationRepository;
    }

    @Transactional(readOnly = true)
    public List<CourseDto> getAvailableCoursesForMemberId(Long memberId) {
        LocalDateTime now = LocalDateTime.now();
        Date currentDate = new Date();

        return courseRepository.findAll().stream()
                .filter(course -> isCourseAvailableForMember(course, memberId, now, currentDate))
                .map(this::mapToCourseDto)
                .collect(Collectors.toList());
    }

    private boolean isCourseAvailableForMember(Course course, Long memberId, LocalDateTime now, Date currentDate) {

        boolean isRegistrationPeriodOpen = course.getRegistrationEndDate().isAfter(now) && course.getSeason().getEndDate().after(currentDate);
        boolean isMemberNotRegisteredForSeason = !registrationRepository.existsByMemberIdAndCourseSeasonId(memberId, course.getSeason().getId());
        
        return isRegistrationPeriodOpen && isMemberNotRegisteredForSeason;
    }

    private CourseDto mapToCourseDto(Course course) {
        long registrationsCount = registrationRepository.countByCourseId(course.getId());
        int remainingSlots = course.getMaxMembers() - (int) registrationsCount;

        SeasonDto seasonDto = mapToSeasonDto(course.getSeason());
        ProgramDto programDto = mapToProgramDto(course.getProgram());
        List<TrainingSlotDto> trainingSlotDtos = mapToTrainingSlotDtos(course.getTrainingSlots());

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
                remainingSlots
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
