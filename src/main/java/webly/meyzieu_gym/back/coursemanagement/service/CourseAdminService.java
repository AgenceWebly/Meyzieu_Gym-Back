package webly.meyzieu_gym.back.coursemanagement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webly.meyzieu_gym.back.coursemanagement.dto.CourseDto;
import webly.meyzieu_gym.back.coursemanagement.dto.TrainingSlotDto;
import webly.meyzieu_gym.back.coursemanagement.entity.Course;
import webly.meyzieu_gym.back.coursemanagement.entity.TrainingSlot;
import webly.meyzieu_gym.back.coursemanagement.repository.CourseRepository;
import webly.meyzieu_gym.back.registrationmanagement.repository.RegistrationRepository;

@Service
public class CourseAdminService {
    
    private final CourseRepository courseRepository;
    private final RegistrationRepository registrationRepository;

    public CourseAdminService(CourseRepository courseRepository, RegistrationRepository registrationRepository) {
        this.courseRepository = courseRepository;
        this.registrationRepository = registrationRepository;
    }

    @Transactional(readOnly = true)
    public List<CourseDto> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(this::mapToCourseDto)
                .collect(Collectors.toList());
    }

    private CourseDto mapToCourseDto(Course course) {
        List<TrainingSlotDto> trainingSlots = course.getTrainingSlots().stream()
                .map(this::mapToTrainingSlotDto)
                .collect(Collectors.toList());
                long registrationsCount = registrationRepository.countByCourseId(course.getId());
                int remainingSlots = course.getMaxMembers() - (int) registrationsCount;

        return new CourseDto(
                course.getId(),
                course.getSeason().getId(),
                course.getProgram().getId(),
                course.getRegistrationStartDate(),
                course.getRegistrationEndDate(),
                course.getPrice(),
                course.getMaxMembers(),
                course.getMinAge(),
                course.getMaxAge(),
                trainingSlots,
                remainingSlots
        );
    }

    private TrainingSlotDto mapToTrainingSlotDto(TrainingSlot trainingSlot) {
        return new TrainingSlotDto(
                trainingSlot.getId(),
                trainingSlot.getDay(),
                trainingSlot.getStartTime(),
                trainingSlot.getEndTime()
        );
    }
}
