package webly.meyzieu_gym.back.coursemanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import webly.meyzieu_gym.back.coursemanagement.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{
    Optional<Course> findByProgramIdAndSeasonIdAndMinAgeAndMaxAgeAndCourseName(Long programId, Long seasonId, Integer minAge, Integer maxAge, String courseName);
}
