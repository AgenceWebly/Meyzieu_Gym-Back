package webly.meyzieu_gym.back.userManagement.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import webly.meyzieu_gym.back.common.exception.custom.UserNotFoundException;
import webly.meyzieu_gym.back.usermanagement.user.dto.UserProfileDto;
import webly.meyzieu_gym.back.usermanagement.user.entity.User;
import webly.meyzieu_gym.back.usermanagement.user.repository.UserRepository;
import webly.meyzieu_gym.back.usermanagement.user.service.UserService;

class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserById_UserFound() {
        // Given
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setEmail("john.doe@example.com");
        user.setPhoneNumber("1234567890");
        user.setAddress("123 Street");
        user.setOccupation("Sith Lord");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // When
        UserProfileDto userProfileDto = userService.getUserById(userId);

        // Then
        assertEquals(userId, userProfileDto.getId());
        assertEquals("John", userProfileDto.getFirstname());
        assertEquals("Doe", userProfileDto.getLastname());
        assertEquals("john.doe@example.com", userProfileDto.getEmail());
        assertEquals("1234567890", userProfileDto.getPhoneNumber());
        assertEquals("123 Street", userProfileDto.getAddress());
        assertEquals("Sith Lord", userProfileDto.getOccupation());
    }

    @Test
    void testGetUserById_UserNotFound() {
        // Given
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(userId));
    }
}
