package webly.meyzieu_gym.back.coursemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import webly.meyzieu_gym.back.coursemanagement.entity.Program;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {
}