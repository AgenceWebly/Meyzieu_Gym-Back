package webly.meyzieu_gym.back.coursemanagement.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import webly.meyzieu_gym.back.coursemanagement.dto.CourseDto;
import webly.meyzieu_gym.back.coursemanagement.dto.TrainingSlotDto;
import webly.meyzieu_gym.back.coursemanagement.entity.Course;
import webly.meyzieu_gym.back.coursemanagement.repository.CourseRepository;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<CourseDto> getAvailableCoursesForRegistration() {
        Date currentDate = new Date();

        return courseRepository.findAll().stream()
                .filter(course -> course.getRegistrationEndDate().isAfter(LocalDateTime.now())
                                  && course.getSeason().getEndDate().after(currentDate))
                .map(this::mapToAvailableCourseDto)
                .collect(Collectors.toList());
    }

    private CourseDto mapToAvailableCourseDto(Course course) {
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
                course.getTrainingSlots()
                    .stream()
                    .map(slot -> new TrainingSlotDto(
                        slot.getId(), 
                        slot.getDay(), 
                        slot.getStartTime(), 
                        slot.getEndTime()))
                    .collect(Collectors.toList())
        );
    }
}
