package webly.meyzieu_gym.back.membermanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import webly.meyzieu_gym.back.membermanagement.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT m FROM Member m JOIN MemberGuardian mg ON m.id = mg.member.id WHERE mg.user.id = :userId")
    List<Member> findAllByUserId(@Param("userId") Long userId);
}
