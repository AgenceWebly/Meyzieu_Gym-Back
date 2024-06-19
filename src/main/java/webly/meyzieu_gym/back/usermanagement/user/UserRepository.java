package webly.meyzieu_gym.back.usermanagement.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByEmail(String email);
  
    Boolean existsByEmail(String email);

    Optional<User> findById(Long id);

    List<User> findAll();

}
