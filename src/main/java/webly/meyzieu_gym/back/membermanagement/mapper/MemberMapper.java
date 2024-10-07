package webly.meyzieu_gym.back.membermanagement.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import webly.meyzieu_gym.back.membermanagement.dto.MemberDto;
import webly.meyzieu_gym.back.membermanagement.entity.Member;

@Component
public class MemberMapper {

    public MemberDto mapToDto(Member member) {
        return new MemberDto(
                member.getId(),
                member.getFirstname(),
                member.getLastname(),
                member.isAllowedToLeave(),
                member.isFirstAidApproved(),
                member.isPhotoApproved(),
                member.isTransportApproved(),
                member.getProfilePictureUrl(),
                member.getSportPassUrl(),
                member.getRegionPassUrl()
        );
    }

    public List<MemberDto> mapToMemberDtos(List<Member> members) {
        return members.stream()
                .map(member -> new MemberDto(
                        member.getId(),
                        member.getFirstname(),
                        member.getLastname(),
                        member.isAllowedToLeave(),
                        member.isFirstAidApproved(),
                        member.isPhotoApproved(),
                        member.isTransportApproved(),
                        member.getProfilePictureUrl(),
                        member.getSportPassUrl(),
                        member.getRegionPassUrl()
                ))
                .collect(Collectors.toList());
    }
}
