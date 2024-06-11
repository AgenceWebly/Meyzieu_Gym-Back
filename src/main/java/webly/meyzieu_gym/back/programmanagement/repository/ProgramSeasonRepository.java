package webly.meyzieu_gym.back.programmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import webly.meyzieu_gym.back.programmanagement.entity.ProgramSeason;

public interface ProgramSeasonRepository extends JpaRepository<ProgramSeason, Long>{
    Optional<ProgramSeason> findByProgramIdAndSeasonIdAndMinAgeAndMaxAge(Long programId, Long seasonId, Integer minAge, Integer maxAge);
}
