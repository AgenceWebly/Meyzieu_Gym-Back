package webly.meyzieu_gym.back.coursemanagement.service;

import java.util.List;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import webly.meyzieu_gym.back.common.exception.custom.DuplicateCourseException;
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
import webly.meyzieu_gym.back.coursemanagement.repository.TrainingSlotRepository;

@Service
public class CourseService {
    
    private final CourseRepository courseRepository;
    private final SeasonRepository seasonRepository;
    private final ProgramRepository programRepository;
    private final TrainingSlotRepository trainingSlotRepository;

    public CourseService(CourseRepository courseRepository, SeasonRepository seasonRepository, ProgramRepository programRepository, TrainingSlotRepository trainingSlotRepository) {
        this.courseRepository = courseRepository;
        this.seasonRepository = seasonRepository;
        this.programRepository = programRepository;
        this.trainingSlotRepository = trainingSlotRepository;
    }

    @Transactional
    public void createCourse(CreateCourseDto createCourseDto) {
        Season season = seasonRepository.findById(createCourseDto.getSeasonId())
                .orElseThrow(() -> new SeasonNotFoundException("Season not found"));
        Program program = programRepository.findById(createCourseDto.getProgramId())
                .orElseThrow(() -> new ProgramNotFoundException("Program not found"));

        courseRepository.findByProgramIdAndSeasonIdAndMinAgeAndMaxAge(
            createCourseDto.getProgramId(),
            createCourseDto.getSeasonId(),
            createCourseDto.getMinAge(),
            createCourseDto.getMaxAge()
        ).ifPresent(existingProgramSeason -> {
            throw new DuplicateCourseException("A course with the same program, season, minAge, and maxAge already exists.");
        });

        Course course = new Course(
            season, 
            program, 
            createCourseDto.getRegistrationStartDate(),
            createCourseDto.getRegistrationEndDate(), 
            createCourseDto.getPrice(),
            createCourseDto.getMaxMembers(), 
            createCourseDto.getMinAge(),
            createCourseDto.getMaxAge());

        courseRepository.save(course);
        saveTrainingSlots(createCourseDto.getCreateTrainingSlotDtos(), course);
    }

    private void saveTrainingSlots(List<CreateTrainingSlotDto> createTrainingSlotDtos, Course course) {
        for (CreateTrainingSlotDto createTrainingSlotDto : createTrainingSlotDtos) {
            TrainingSlot trainingSlot = new TrainingSlot();
            trainingSlot.setDay(createTrainingSlotDto.getDay());
            trainingSlot.setStartTime(createTrainingSlotDto.getStartTime());
            trainingSlot.setEndTime(createTrainingSlotDto.getEndTime());

            trainingSlot.setCourse(course);
            trainingSlotRepository.save(trainingSlot);
        }
    }
}
