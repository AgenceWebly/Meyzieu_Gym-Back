package webly.meyzieu_gym.back.usermanagement.user.service;

import org.springframework.stereotype.Service;

import webly.meyzieu_gym.back.usermanagement.user.dto.AddRibUrlDto;
import webly.meyzieu_gym.back.usermanagement.user.entity.GuardianInfo;
import webly.meyzieu_gym.back.usermanagement.user.entity.User;
import webly.meyzieu_gym.back.usermanagement.user.repository.UserRepository;

@Service
public class RibService {


    private final UserRepository userRepository;

    public RibService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addRibUrlToUser(Long userId, AddRibUrlDto addRibUrlDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("L'utilisateur n'a pas été trouvé"));

        GuardianInfo guardianInfo = user.getGuardianInfo();
        if (guardianInfo == null) {
            guardianInfo = new GuardianInfo();
            guardianInfo.setUser(user);
        }

        guardianInfo.setRibUrl(addRibUrlDto.getRibUrl());
    }
}
