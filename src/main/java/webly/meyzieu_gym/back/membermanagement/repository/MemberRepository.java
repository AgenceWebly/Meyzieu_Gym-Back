package webly.meyzieu_gym.back.membermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import webly.meyzieu_gym.back.membermanagement.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    
}