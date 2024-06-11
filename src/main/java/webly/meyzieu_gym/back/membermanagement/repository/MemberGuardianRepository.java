package webly.meyzieu_gym.back.membermanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import webly.meyzieu_gym.back.membermanagement.entity.MemberGuardian;

public interface MemberGuardianRepository extends JpaRepository<MemberGuardian, Long> {

    Optional<MemberGuardian> findByMemberIdAndUserId(Long memberId, Long userId);

}