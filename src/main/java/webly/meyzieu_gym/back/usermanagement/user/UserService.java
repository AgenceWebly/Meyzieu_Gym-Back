package webly.meyzieu_gym.back.usermanagement.user;

import java.util.Optional;

public interface UserService {

    Optional<User> getUserById(Long id);
}
