package webly.meyzieu_gym.back.membermanagement;

import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import webly.meyzieu_gym.back.membermanagement.dto.CreateMemberDto;
import webly.meyzieu_gym.back.membermanagement.entity.Member;
import webly.meyzieu_gym.back.membermanagement.repository.MemberRepository;
import webly.meyzieu_gym.back.membermanagement.service.CreateMemberService;
import webly.meyzieu_gym.back.usermanagement.user.entity.User;
import webly.meyzieu_gym.back.usermanagement.user.repository.UserRepository;

@SpringBootTest
@Transactional
@Rollback
class CreateMemberServiceIntegrationTest {

    @Autowired
    private CreateMemberService createMemberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        User user = new User();
        user.setId(1L);
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setEmail("john.doe@example.com");

        userRepository.save(user);
    }
    
    @Test
    void testCreateMember_Success() {
        // Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(2010, Calendar.JANUARY, 1);
        Date birthdate = calendar.getTime();

        CreateMemberDto createMemberDto = new CreateMemberDto();
        createMemberDto.setFirstname("Jane");
        createMemberDto.setLastname("Doe");
        createMemberDto.setBirthdate(birthdate);
        createMemberDto.setGender("FEMALE");
        createMemberDto.setSchool("Springfield High");
        createMemberDto.setPhotoApproved(true);
        createMemberDto.setTransportApproved(true);
        createMemberDto.setFirstAidApproved(true);
        createMemberDto.setAllowedToLeave(true);
        createMemberDto.setProfilePictureUrl("http://example.com/jane.jpg");

        // Act
        Long memberId = createMemberService.createMember(createMemberDto, 1L);

        // Assert
        assertThat(memberId).isNotNull();
        Member createdMember = memberRepository.findById(memberId).orElse(null);
        assertThat(createdMember).isNotNull();
        assertThat(createdMember.getFirstname()).isEqualTo("Jane");
        assertThat(createdMember.getLastname()).isEqualTo("Doe");
        assertThat(createdMember.getBirthdate()).isEqualTo(birthdate);
    }

}