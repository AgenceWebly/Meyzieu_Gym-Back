package webly.meyzieu_gym.back.usermanagement.admin;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import webly.meyzieu_gym.back.common.exception.custom.UserNotFoundException;
import webly.meyzieu_gym.back.usermanagement.user.entity.User;
import webly.meyzieu_gym.back.usermanagement.user.repository.UserRepository;

@Service
public class AdminService {
    
    private final UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<UserProfileForAdminDto> getUsersForAdmin() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new UserNotFoundException("Aucun utilisateur trouvé");
        }
        return users.stream()
                    .map(this::mapToAdminDto)
                    .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserProfileForAdminDto getUserById(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("L'utilisateur n'a pas été trouvé"));
        return mapToAdminDto(user);
    }

    private UserProfileForAdminDto mapToAdminDto(User user) {
        Set<String> roles = user.getRoles().stream()
                                .map(role -> role.getName().name())
                                .collect(Collectors.toSet());

        return new UserProfileForAdminDto(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getAddress(),
                user.getOccupation(),
                roles
        );
    }
}
