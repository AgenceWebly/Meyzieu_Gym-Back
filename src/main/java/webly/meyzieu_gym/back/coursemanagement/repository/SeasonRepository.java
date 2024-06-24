package webly.meyzieu_gym.back.coursemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import webly.meyzieu_gym.back.coursemanagement.entity.Season;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Long> {
}
