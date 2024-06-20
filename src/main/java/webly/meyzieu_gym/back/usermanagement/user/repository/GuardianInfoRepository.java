package webly.meyzieu_gym.back.usermanagement.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import webly.meyzieu_gym.back.usermanagement.user.entity.GuardianInfo;

@Repository
public interface GuardianInfoRepository extends JpaRepository<GuardianInfo, Long> {

}
