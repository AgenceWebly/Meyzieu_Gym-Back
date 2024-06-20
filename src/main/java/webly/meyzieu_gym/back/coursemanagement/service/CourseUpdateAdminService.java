package webly.meyzieu_gym.back.coursemanagement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import webly.meyzieu_gym.back.common.exception.custom.CourseNotFoundException;
import webly.meyzieu_gym.back.common.exception.custom.ProgramNotFoundException;
import webly.meyzieu_gym.back.common.exception.custom.SeasonNotFoundException;
import webly.meyzieu_gym.back.coursemanagement.dto.CourseDto;
import webly.meyzieu_gym.back.coursemanagement.dto.CreateCourseDto;
import webly.meyzieu_gym.back.coursemanagement.dto.CreateTrainingSlotDto;
import webly.meyzieu_gym.back.coursemanagement.dto.ProgramDto;
import webly.meyzieu_gym.back.coursemanagement.dto.SeasonDto;
import webly.meyzieu_gym.back.coursemanagement.dto.TrainingSlotDto;
import webly.meyzieu_gym.back.coursemanagement.entity.Course;
import webly.meyzieu_gym.back.coursemanagement.entity.Program;
import webly.meyzieu_gym.back.coursemanagement.entity.Season;
import webly.meyzieu_gym.back.coursemanagement.entity.TrainingSlot;
import webly.meyzieu_gym.back.coursemanagement.repository.CourseRepository;
import webly.meyzieu_gym.back.coursemanagement.repository.ProgramRepository;
import webly.meyzieu_gym.back.coursemanagement.repository.SeasonRepository;
import webly.meyzieu_gym.back.registrationmanagement.repository.RegistrationRepository;

@Service
public class CourseUpdateAdminService {

    private final CourseRepository courseRepository;
    private final SeasonRepository seasonRepository;
    private final ProgramRepository programRepository;
    private final RegistrationRepository registrationRepository;

    public CourseUpdateAdminService(
            CourseRepository courseRepository, 
            SeasonRepository seasonRepository,
            ProgramRepository programRepository, 
            RegistrationRepository registrationRepository) {
        this.courseRepository = courseRepository;
        this.seasonRepository = seasonRepository;
        this.programRepository = programRepository;
        this.registrationRepository = registrationRepository;
    }

    @Transactional
    public CourseDto updateCourse(Long id, CreateCourseDto updateCourseDto) {
        
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

        course = courseRepository.save(course);

        return mapToCourseDto(course);
    }

    private void updateTrainingSlots(Course course, List<CreateTrainingSlotDto> trainingSlotDtos) {
        List<TrainingSlot> updatedTrainingSlots = trainingSlotDtos.stream()
                .map(dto -> new TrainingSlot(dto.getDay(), dto.getStartTime(), dto.getEndTime(), course))
                .collect(Collectors.toList());

        course.getTrainingSlots().clear();
        course.getTrainingSlots().addAll(updatedTrainingSlots);
    }

    private CourseDto mapToCourseDto(Course course) {
        int remainingSlots = calculateRemainingSlots(course);

        SeasonDto seasonDto = mapToSeasonDto(course.getSeason());
        ProgramDto programDto = mapToProgramDto(course.getProgram());
        List<TrainingSlotDto> trainingSlotDtos = mapToTrainingSlotDtos(course.getTrainingSlots());

        return new CourseDto(
                course.getId(),
                seasonDto,
                programDto,
                course.getCourseName(),
                course.getRegistrationStartDate(),
                course.getRegistrationEndDate(),
                course.getPrice(),
                course.getMaxMembers(),
                course.getMinAge(),
                course.getMaxAge(),
                trainingSlotDtos,
                remainingSlots,
                null
        );
    }

    private int calculateRemainingSlots(Course course) {
        long registrationsCount = registrationRepository.countByCourseId(course.getId());
        return course.getMaxMembers() - (int) registrationsCount;
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
