package webly.meyzieu_gym.back.usermanagement.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import webly.meyzieu_gym.back.common.exception.custom.UserNotFoundException;
import webly.meyzieu_gym.back.usermanagement.user.dto.UserProfileDto;
import webly.meyzieu_gym.back.usermanagement.user.dto.UserUpdateDto;
import webly.meyzieu_gym.back.usermanagement.user.entity.User;
import webly.meyzieu_gym.back.usermanagement.user.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public UserProfileDto getUserById(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("L'utilisateur n'a pas été trouvé"));
        return mapToDto(user);
    }

    @Transactional
    public UserProfileDto updateUser(Long id, UserUpdateDto updatedProfileDto){
        User user = userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("L'utilisateur n'a pas été trouvé"));

        user.setPhoneNumber(updatedProfileDto.getPhoneNumber());
        user.setAddress(updatedProfileDto.getAddress());
        user.setOccupation(updatedProfileDto.getOccupation());
        user.setRibUrl(updatedProfileDto.getRibUrl());

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
            user.getOccupation(),
            user.getRibUrl()
        );
    }
}
