package webly.meyzieu_gym.back.usermanagement.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByEmail(String email);
  
    Boolean existsByEmail(String email);

    Optional<User> findById(Long id);

}
