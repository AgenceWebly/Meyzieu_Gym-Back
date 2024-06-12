package webly.meyzieu_gym.back.membermanagement.service;

import org.springframework.stereotype.Service;

import webly.meyzieu_gym.back.membermanagement.repository.MemberGuardianRepository;

@Service
public class MemberOwnershipService {

    private final MemberGuardianRepository memberGuardianRepository;

    public MemberOwnershipService(MemberGuardianRepository memberGuardianRepository) {
        this.memberGuardianRepository = memberGuardianRepository;
    }

    public boolean isMemberOwner(Long memberId, Long userId) {
        return memberGuardianRepository.findByMemberIdAndUserId(memberId, userId).isPresent();
    }
}