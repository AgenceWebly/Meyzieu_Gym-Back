package webly.meyzieu_gym.back.membermanagement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import webly.meyzieu_gym.back.membermanagement.dto.MemberListDto;
import webly.meyzieu_gym.back.membermanagement.entity.Member;
import webly.meyzieu_gym.back.membermanagement.repository.MemberRepository;

@Service
public class MembersByUserService {

    private final MemberRepository memberRepository;

    public MembersByUserService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<MemberListDto> getMembersByUserId(Long userId, boolean forRegistration) {
        List<Member> members = memberRepository.findAllByUserId(userId);

        if (forRegistration) {
            members = members.stream()
                .filter(member -> !isRegisteredForUpcomingSeason(member) || !isRegistrationValidated(member))
                .collect(Collectors.toList());
        }

        return members.stream()
            .map(this::mapToMemberListDto)
            .collect(Collectors.toList());
    }

    private boolean isRegisteredForUpcomingSeason(Member member) {
        return false;
        // Implement logic to check if member is registered for upcoming season
    }

    private boolean isRegistrationValidated(Member member) {
        return true;
        // Implement logic to check if registration status is "registration validated"
    }

    private MemberListDto mapToMemberListDto(Member member) {
        return new MemberListDto(
            member.getId(), 
            member.getFirstname(),
            member.getLastname(), 
            member.getProfilePictureUrl());
    }
}
