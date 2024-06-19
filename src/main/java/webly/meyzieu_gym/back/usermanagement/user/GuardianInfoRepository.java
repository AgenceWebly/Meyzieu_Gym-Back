package webly.meyzieu_gym.back.usermanagement.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuardianInfoRepository extends JpaRepository<GuardianInfo, Long> {

}
