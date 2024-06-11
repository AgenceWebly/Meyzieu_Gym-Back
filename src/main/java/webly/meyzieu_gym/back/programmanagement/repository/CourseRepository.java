package webly.meyzieu_gym.back.programmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import webly.meyzieu_gym.back.programmanagement.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{
    Optional<Course> findByProgramIdAndSeasonIdAndMinAgeAndMaxAge(Long programId, Long seasonId, Integer minAge, Integer maxAge);
}
