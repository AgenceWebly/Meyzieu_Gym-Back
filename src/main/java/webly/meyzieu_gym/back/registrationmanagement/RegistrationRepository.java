package webly.meyzieu_gym.back.registrationmanagement;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long>{
    boolean existsByMemberIdAndCourseSeasonId(Long memberId, Long seasonId);
}
