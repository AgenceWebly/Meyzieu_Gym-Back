package webly.meyzieu_gym.back.coursemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import webly.meyzieu_gym.back.coursemanagement.entity.TrainingSlot;

@Repository
public interface TrainingSlotRepository extends JpaRepository<TrainingSlot, Long>{ 
}