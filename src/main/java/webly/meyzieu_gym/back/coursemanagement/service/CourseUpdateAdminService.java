package webly.meyzieu_gym.back.coursemanagement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import webly.meyzieu_gym.back.common.exception.custom.CourseNotFoundException;
import webly.meyzieu_gym.back.common.exception.custom.ProgramNotFoundException;
import webly.meyzieu_gym.back.common.exception.custom.SeasonNotFoundException;
import webly.meyzieu_gym.back.coursemanagement.dto.CreateCourseDto;
import webly.meyzieu_gym.back.coursemanagement.dto.CreateTrainingSlotDto;
import webly.meyzieu_gym.back.coursemanagement.entity.Course;
import webly.meyzieu_gym.back.coursemanagement.entity.Program;
import webly.meyzieu_gym.back.coursemanagement.entity.Season;
import webly.meyzieu_gym.back.coursemanagement.entity.TrainingSlot;
import webly.meyzieu_gym.back.coursemanagement.repository.CourseRepository;
import webly.meyzieu_gym.back.coursemanagement.repository.ProgramRepository;
import webly.meyzieu_gym.back.coursemanagement.repository.SeasonRepository;

@Service
public class CourseUpdateAdminService {

    private final CourseRepository courseRepository;
    private final SeasonRepository seasonRepository;
    private final ProgramRepository programRepository;

    public CourseUpdateAdminService(
            CourseRepository courseRepository, 
            SeasonRepository seasonRepository,
            ProgramRepository programRepository) {
        this.courseRepository = courseRepository;
        this.seasonRepository = seasonRepository;
        this.programRepository = programRepository;
    }

    @Transactional
    public void updateCourse(Long id, CreateCourseDto updateCourseDto) {
        
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Le cours n'a pas été trouvé"));
        Season season = seasonRepository.findById(updateCourseDto.getSeasonId())
                .orElseThrow(() -> new SeasonNotFoundException("La saison n'a pas été trouvée"));
        Program program = programRepository.findById(updateCourseDto.getProgramId())
                .orElseThrow(() -> new ProgramNotFoundException("Le programme n'a pas été trouvé"));
        
        course.setSeason(season);
        course.setProgram(program);
        course.setCourseName(updateCourseDto.getCourseName());
        course.setRegistrationStartDate(updateCourseDto.getRegistrationStartDate());
        course.setRegistrationEndDate(updateCourseDto.getRegistrationEndDate());
        course.setPrice(updateCourseDto.getPrice());
        course.setMaxMembers(updateCourseDto.getMaxMembers());
        course.setMinAge(updateCourseDto.getMinAge());
        course.setMaxAge(updateCourseDto.getMaxAge());

        course = courseRepository.save(course);

        updateTrainingSlots(course, updateCourseDto.getCreateTrainingSlotDtos());

        courseRepository.save(course);
    }

    private void updateTrainingSlots(Course course, List<CreateTrainingSlotDto> trainingSlotDtos) {
        List<TrainingSlot> updatedTrainingSlots = trainingSlotDtos.stream()
                .map(dto -> new TrainingSlot(dto.getDay(), dto.getStartTime(), dto.getEndTime(), course))
                .collect(Collectors.toList());

        course.getTrainingSlots().clear();
        course.getTrainingSlots().addAll(updatedTrainingSlots);
    }
}
