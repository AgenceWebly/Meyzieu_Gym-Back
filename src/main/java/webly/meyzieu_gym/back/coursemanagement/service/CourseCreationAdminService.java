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
public class CourseCreationAdminService {
    
    private final CourseRepository courseRepository;
    private final SeasonRepository seasonRepository;
    private final ProgramRepository programRepository;
    private final TrainingSlotRepository trainingSlotRepository;

    public CourseCreationAdminService(CourseRepository courseRepository, SeasonRepository seasonRepository, ProgramRepository programRepository, TrainingSlotRepository trainingSlotRepository) {
        this.courseRepository = courseRepository;
        this.seasonRepository = seasonRepository;
        this.programRepository = programRepository;
        this.trainingSlotRepository = trainingSlotRepository;
    }

    @Transactional
    public void createCourse(CreateCourseDto createCourseDto) {
        Season season = seasonRepository.findById(createCourseDto.getSeasonId())
                .orElseThrow(() -> new SeasonNotFoundException("La saison n'a pas été trouvée"));
        Program program = programRepository.findById(createCourseDto.getProgramId())
                .orElseThrow(() -> new ProgramNotFoundException("Le programme n'a pas été trouvé"));

        courseRepository.findByProgramIdAndSeasonIdAndMinAgeAndMaxAgeAndCourseName(
            createCourseDto.getProgramId(),
            createCourseDto.getSeasonId(),
            createCourseDto.getMinAge(),
            createCourseDto.getMaxAge(),
            createCourseDto.getCourseName()
        ).ifPresent(existingProgramSeason -> {
            throw new DuplicateCourseException("Un cours avec le même programme, saison, âge minimum, âge maximum et nom existe déjà.");
        });

        Course course = new Course(
            season, 
            program, 
            createCourseDto.getCourseName(),
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
