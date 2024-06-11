package webly.meyzieu_gym.back.programmanagement.service;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import webly.meyzieu_gym.back.common.exception.custom.DuplicateCourseException;
import webly.meyzieu_gym.back.common.exception.custom.ProgramNotFoundException;
import webly.meyzieu_gym.back.common.exception.custom.SeasonNotFoundException;
import webly.meyzieu_gym.back.programmanagement.dto.CourseDto;
import webly.meyzieu_gym.back.programmanagement.entity.Program;
import webly.meyzieu_gym.back.programmanagement.entity.Course;
import webly.meyzieu_gym.back.programmanagement.entity.Season;
import webly.meyzieu_gym.back.programmanagement.repository.ProgramRepository;
import webly.meyzieu_gym.back.programmanagement.repository.CourseRepository;
import webly.meyzieu_gym.back.programmanagement.repository.SeasonRepository;

@Service
public class CourseService {
    
    private final CourseRepository courseRepository;
    private final SeasonRepository seasonRepository;
    private final ProgramRepository programRepository;

    public CourseService(CourseRepository courseRepository, SeasonRepository seasonRepository, ProgramRepository programRepository) {
        this.courseRepository = courseRepository;
        this.seasonRepository = seasonRepository;
        this.programRepository = programRepository;
    }

    @Transactional
    public void createCourse(CourseDto createCourseDto) {
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
    }
}
