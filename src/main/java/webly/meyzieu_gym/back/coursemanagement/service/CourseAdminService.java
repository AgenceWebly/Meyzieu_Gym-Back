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

@Service
public class CourseAdminService {
    
    private final CourseRepository courseRepository;

    public CourseAdminService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional(readOnly = true)
    public List<CourseDto> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(this::mapToCourseDto)
                .collect(Collectors.toList());
    }

    private CourseDto mapToCourseDto(Course course) {
        List<TrainingSlotDto> trainingSlotDtos = course.getTrainingSlots().stream()
                .map(this::mapToTrainingSlotDto)
                .collect(Collectors.toList());

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
                trainingSlotDtos
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
