package webly.meyzieu_gym.back.membermanagement;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import webly.meyzieu_gym.back.common.exception.custom.UserNotFoundException;
import webly.meyzieu_gym.back.membermanagement.dto.CreateMemberDto;
import webly.meyzieu_gym.back.membermanagement.dto.EmergencyContactDto;
import webly.meyzieu_gym.back.membermanagement.entity.EmergencyContact;
import webly.meyzieu_gym.back.membermanagement.entity.Member;
import webly.meyzieu_gym.back.membermanagement.entity.MemberGuardian;
import webly.meyzieu_gym.back.membermanagement.repository.EmergencyContactRepository;
import webly.meyzieu_gym.back.membermanagement.repository.MemberGuardianRepository;
import webly.meyzieu_gym.back.membermanagement.repository.MemberRepository;
import webly.meyzieu_gym.back.membermanagement.service.CreateMemberService;
import webly.meyzieu_gym.back.usermanagement.user.entity.User;
import webly.meyzieu_gym.back.usermanagement.user.repository.UserRepository;

@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@ImportAutoConfiguration(exclude = SecurityAutoConfiguration.class)
class CreateMemberServiceTest {
    
    @Mock
    private UserRepository userRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private MemberGuardianRepository memberGuardianRepository;

    @Mock
    private EmergencyContactRepository emergencyContactRepository;

    @InjectMocks
    private CreateMemberService createMemberService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createMember_ShouldReturnMemberId_WhenSuccessful() {
        // Arrange
        Long userId = 1L;
        Long memberId = 2L;

        User user = new User();
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        Member member = new Member();
        member.setId(memberId);
        when(memberRepository.save(any(Member.class))).thenReturn(member);

        CreateMemberDto createMemberDto = new CreateMemberDto();
        createMemberDto.setFirstname("John");
        createMemberDto.setLastname("Doe");
        createMemberDto.setBirthdate(new Date());
        createMemberDto.setGender("Male");
        createMemberDto.setSchool("Some School");
        createMemberDto.setPhotoApproved(true);
        createMemberDto.setTransportApproved(false);
        createMemberDto.setFirstAidApproved(true);
        createMemberDto.setAllowedToLeave(true);
        createMemberDto.setProfilePictureUrl("http://example.com/profile.jpg");
        createMemberDto.setRelationToMember("Father");

        EmergencyContactDto emergencyContactDto = new EmergencyContactDto();
        emergencyContactDto.setFirstname("Jane");
        emergencyContactDto.setLastname("Doe");
        emergencyContactDto.setRelationToMember("Mother");
        emergencyContactDto.setPhoneNumber("123456789");
        createMemberDto.setEmergencyContacts(Arrays.asList(emergencyContactDto));

        // Act
        Long result = createMemberService.createMember(createMemberDto, userId);

        // Assert
        assertEquals(memberId, result);
        verify(memberRepository, times(1)).save(any(Member.class));
        verify(memberGuardianRepository, times(1)).save(any(MemberGuardian.class));
        verify(emergencyContactRepository, times(1)).save(any(EmergencyContact.class));
    }

    @Test
    void createMember_ShouldThrowUserNotFoundException_WhenUserNotFound() {
        // Arrange
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        CreateMemberDto createMemberDto = new CreateMemberDto();

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> createMemberService.createMember(createMemberDto, userId));
    }

    @Test
    void createMember_ShouldThrowException_WhenUserIdIsNull() {
        // Arrange
        Long userId = null;
        CreateMemberDto createMemberDto = new CreateMemberDto();

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> createMemberService.createMember(createMemberDto, userId));
    }

    @Test
    void createMember_ShouldThrowException_WhenMemberSaveFails() {
        // Arrange
        Long userId = 1L;
    
        User user = new User();
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
    
        when(memberRepository.save(any(Member.class))).thenThrow(new RuntimeException("Save failed"));
    
        CreateMemberDto createMemberDto = new CreateMemberDto();
        createMemberDto.setFirstname("John");
        createMemberDto.setLastname("Doe");
        createMemberDto.setBirthdate(new Date());
        createMemberDto.setGender("Male");
    
        // Act & Assert
        assertThrows(RuntimeException.class, () -> createMemberService.createMember(createMemberDto, userId));
    }

    @Test
    void createMember_ShouldSaveMultipleEmergencyContacts() {
        // Arrange
        Long userId = 1L;
        Long memberId = 2L;

        User user = new User();
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        Member member = new Member();
        member.setId(memberId);
        when(memberRepository.save(any(Member.class))).thenReturn(member);

        CreateMemberDto createMemberDto = new CreateMemberDto();
        createMemberDto.setFirstname("John");
        createMemberDto.setLastname("Doe");
        createMemberDto.setBirthdate(new Date());
        createMemberDto.setGender("Male");
        createMemberDto.setSchool("Some School");
        createMemberDto.setPhotoApproved(true);
        createMemberDto.setTransportApproved(false);
        createMemberDto.setFirstAidApproved(true);
        createMemberDto.setAllowedToLeave(true);
        createMemberDto.setProfilePictureUrl("http://example.com/profile.jpg");
        createMemberDto.setRelationToMember("Father");

        EmergencyContactDto contact1 = new EmergencyContactDto();
        contact1.setFirstname("Jane");
        contact1.setLastname("Doe");
        contact1.setRelationToMember("Mother");
        contact1.setPhoneNumber("123456789");

        EmergencyContactDto contact2 = new EmergencyContactDto();
        contact2.setFirstname("Mary");
        contact2.setLastname("Smith");
        contact2.setRelationToMember("Aunt");
        contact2.setPhoneNumber("987654321");

        createMemberDto.setEmergencyContacts(Arrays.asList(contact1, contact2));

        // Act
        Long result = createMemberService.createMember(createMemberDto, userId);

        // Assert
        assertEquals(memberId, result);
        verify(emergencyContactRepository, times(2)).save(any(EmergencyContact.class));
    }
}
