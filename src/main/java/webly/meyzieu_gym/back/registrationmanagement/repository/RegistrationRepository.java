package webly.meyzieu_gym.back.registrationmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import webly.meyzieu_gym.back.registrationmanagement.entity.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Long>{
    boolean existsByMemberIdAndCourseSeasonId(Long memberId, Long seasonId);

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN TRUE ELSE FALSE END " +
       "FROM Registration r " +
       "JOIN r.member m " +
       "JOIN m.guardians g " +
       "WHERE r.id = :registrationId AND g.user.id = :userId")
    boolean existsByIdAndMemberGuardianUserId(@Param("registrationId") Long registrationId, @Param("userId") Long userId);

    long countByCourseId(Long courseId);

    @Query("SELECT COUNT(r) FROM Registration r JOIN r.member m JOIN m.guardians g WHERE g.user.id = :userId AND r.course.season.id = :seasonId")
    long countByUserIdAndSeasonId(@Param("userId") Long userId, @Param("seasonId") Long seasonId);

}
