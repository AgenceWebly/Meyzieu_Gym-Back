package webly.meyzieu_gym.back.registrationmanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import webly.meyzieu_gym.back.common.exception.custom.CourseNotFoundException;
import webly.meyzieu_gym.back.common.exception.custom.MemberNotFoundException;
import webly.meyzieu_gym.back.coursemanagement.entity.Course;
import webly.meyzieu_gym.back.coursemanagement.entity.Season;
import webly.meyzieu_gym.back.coursemanagement.repository.CourseRepository;
import webly.meyzieu_gym.back.membermanagement.entity.Member;
import webly.meyzieu_gym.back.membermanagement.repository.MemberRepository;
import webly.meyzieu_gym.back.registrationmanagement.dto.NewRegistrationDto;
import webly.meyzieu_gym.back.registrationmanagement.entity.Registration;
import webly.meyzieu_gym.back.registrationmanagement.repository.RegistrationRepository;
import webly.meyzieu_gym.back.registrationmanagement.service.RegistrationService;

class MemberRegistrationTest {
    
    @Mock
    private MemberRepository memberRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private RegistrationRepository registrationRepository;

    @InjectMocks
    private RegistrationService registrationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerMember_ShouldRegisterSuccessfully_WhenAllConditionsAreMet() {
        // Arrange
        NewRegistrationDto dto = new NewRegistrationDto();
        dto.setMemberId(1L);
        dto.setCourseId(1L);
        dto.setRegistrationFee(BigDecimal.valueOf(200));
        dto.setRegistrationStatus("Registered");

        Member member = new Member();
        Course course = new Course();
        course.setMaxMembers(10);
        Season season = new Season();
        course.setSeason(season);

        when(memberRepository.findById(anyLong())).thenReturn(java.util.Optional.of(member));
        when(courseRepository.findById(anyLong())).thenReturn(java.util.Optional.of(course));
        when(registrationRepository.countByCourseId(anyLong())).thenReturn(5);
        when(registrationRepository.existsByMemberIdAndCourseSeasonId(anyLong(), anyLong())).thenReturn(false);
        when(registrationRepository.save(any(Registration.class))).thenReturn(new Registration());

        // Act
        Long result = registrationService.registerMember(dto);

        // Assert
        assertEquals(null, result);  // Assuming the registration ID is returned and is 0L in this mock
        verify(memberRepository, times(1)).findById(dto.getMemberId());
        verify(courseRepository, times(1)).findById(dto.getCourseId());
        verify(registrationRepository, times(1)).save(any(Registration.class));
    }

    @Test
    void registerMember_ShouldThrowMemberNotFoundException_WhenMemberDoesNotExist() {
        NewRegistrationDto dto = new NewRegistrationDto();
        dto.setMemberId(1L);
        when(memberRepository.findById(anyLong())).thenReturn(java.util.Optional.empty());

        assertThrows(MemberNotFoundException.class, () -> registrationService.registerMember(dto));
    }

    @Test
    void registerMember_ShouldThrowCourseNotFoundException_WhenCourseDoesNotExist() {
        NewRegistrationDto dto = new NewRegistrationDto();
        dto.setCourseId(1L);
        dto.setMemberId(1L);  // Ensure that the memberId is set
    
        // Mock the member to be found
        when(memberRepository.findById(dto.getMemberId())).thenReturn(Optional.of(new Member()));
        
        // Mock the course not being found
        when(courseRepository.findById(dto.getCourseId())).thenReturn(Optional.empty());
    
        // Assert that CourseNotFoundException is thrown
        assertThrows(CourseNotFoundException.class, () -> registrationService.registerMember(dto));
    }


}
