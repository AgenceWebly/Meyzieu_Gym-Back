package webly.meyzieu_gym.back.membermanagement.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import webly.meyzieu_gym.back.common.exception.custom.MemberNotFoundException;
import webly.meyzieu_gym.back.membermanagement.dto.MemberDto;
import webly.meyzieu_gym.back.membermanagement.dto.UpdateMemberDto;
import webly.meyzieu_gym.back.membermanagement.entity.Member;
import webly.meyzieu_gym.back.membermanagement.repository.MemberRepository;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional(readOnly = true)
    public MemberDto getMemberById(Long id) {
        Optional<Member> memberOptional = memberRepository.findById(id);

        if (!memberOptional.isPresent()) {
            throw new MemberNotFoundException("L'adhérent n'a pas été trouvé");
        }

        Member member = memberOptional.get();
        return mapToMemberDto(member);
    }

    @Transactional
    public void updateMember(Long memberId, UpdateMemberDto updateMemberDto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("L'adhérent n'a pas été trouvé"));

        member.setSchool(updateMemberDto.getSchool());
        member.setProfilePictureUrl(updateMemberDto.getProfilePictureUrl());
        member.setSportPassUrl(updateMemberDto.getSportPassUrl());
        member.setRegionPassUrl(updateMemberDto.getRegionPassUrl());

        memberRepository.save(member);
    }

    private MemberDto mapToMemberDto(Member member) {
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
}
