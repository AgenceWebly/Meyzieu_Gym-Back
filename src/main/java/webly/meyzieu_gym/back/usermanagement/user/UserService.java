package webly.meyzieu_gym.back.usermanagement.user;

import org.springframework.stereotype.Service;

import webly.meyzieu_gym.back.common.exception.custom.UserNotFoundException;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserProfileDto getUserById(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
        return mapToDto(user);
    }

    public UserProfileDto updateUser(Long id, UserProfileDto updatedProfileDto){
        User user = userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setPhoneNumber(updatedProfileDto.getPhoneNumber());
        user.setAddress(updatedProfileDto.getAddress());
        user.setOccupation(updatedProfileDto.getOccupation());

        User updatedUser = userRepository.save(user);

        return mapToDto(updatedUser);
    }

    private UserProfileDto mapToDto(User user) {
        return new UserProfileDto(
            user.getId(),
            user.getFirstname(),
            user.getLastname(),
            user.getEmail(),
            user.getPhoneNumber(),
            user.getAddress(),
            user.getOccupation()
        );
    }
}
