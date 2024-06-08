package webly.meyzieu_gym.back.programmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import webly.meyzieu_gym.back.programmanagement.entity.Program;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {
}