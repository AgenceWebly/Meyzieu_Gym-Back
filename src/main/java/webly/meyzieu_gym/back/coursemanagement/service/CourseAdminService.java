package webly.meyzieu_gym.back.coursemanagement.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import webly.meyzieu_gym.back.common.exception.custom.CourseNotFoundException;
import webly.meyzieu_gym.back.coursemanagement.dto.CourseAdminDto;
import webly.meyzieu_gym.back.coursemanagement.dto.CourseDto;
import webly.meyzieu_gym.back.coursemanagement.dto.ProgramDto;
import webly.meyzieu_gym.back.coursemanagement.dto.SeasonDto;
import webly.meyzieu_gym.back.coursemanagement.dto.TrainingSlotDto;
import webly.meyzieu_gym.back.coursemanagement.entity.Course;
import webly.meyzieu_gym.back.coursemanagement.entity.Program;
import webly.meyzieu_gym.back.coursemanagement.entity.Season;
import webly.meyzieu_gym.back.coursemanagement.entity.TrainingSlot;
import webly.meyzieu_gym.back.coursemanagement.repository.CourseRepository;
import webly.meyzieu_gym.back.membermanagement.dto.MemberDto;
import webly.meyzieu_gym.back.membermanagement.entity.Member;
import webly.meyzieu_gym.back.registrationmanagement.repository.RegistrationRepository;

@Service
public class CourseAdminService {
    
    private final CourseRepository courseRepository;
    private final RegistrationRepository registrationRepository;

    CourseAdminService(CourseRepository courseRepository, RegistrationRepository registrationRepository) {
        this.courseRepository = courseRepository;
        this.registrationRepository = registrationRepository;
    }

    @Transactional(readOnly = true)
    public List<CourseDto> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(this::mapToCourseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CourseAdminDto getCourseById(Long id) {
        Optional<Course> courseOptional = courseRepository.findById(id);

        if (!courseOptional.isPresent()) {
            throw new CourseNotFoundException("Le cours n'a pas été trouvé");
        }

        Course course = courseOptional.get();
        return mapToCourseAdminDto(course);
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

        private CourseAdminDto mapToCourseAdminDto(Course course) {
        int remainingSlots = calculateRemainingSlots(course);

        SeasonDto seasonDto = mapToSeasonDto(course.getSeason());
        ProgramDto programDto = mapToProgramDto(course.getProgram());
        List<TrainingSlotDto> trainingSlotDtos = mapToTrainingSlotDtos(course.getTrainingSlots());
        List<MemberDto> memberDtos = mapToMemberDtos(course.getRegistrations().stream()
                                                   .map(reg -> reg.getMember())
                                                   .collect(Collectors.toList()));

        return new CourseAdminDto(
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
                memberDtos
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
    
    private List<MemberDto> mapToMemberDtos(List<Member> members) {
        return members.stream()
                .map(member -> new MemberDto(
                        member.getId(),
                        member.getFirstname(),
                        member.getLastname(),
                        member.isAllowedToLeave(),
                        member.isFirstAidApproved(),
                        member.isPhotoApproved(),
                        member.isTransportApproved(),
                        member.getProfilePictureUrl(),
                        member.getSportPassUrl(),
                        member.getRegionPassUrl()
                ))
                .collect(Collectors.toList());
    }
}
