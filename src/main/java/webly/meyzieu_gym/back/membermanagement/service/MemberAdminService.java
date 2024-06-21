package webly.meyzieu_gym.back.membermanagement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import webly.meyzieu_gym.back.membermanagement.dto.MemberProfileAdminDto;
import webly.meyzieu_gym.back.membermanagement.entity.Member;
import webly.meyzieu_gym.back.membermanagement.repository.MemberRepository;
import webly.meyzieu_gym.back.registrationmanagement.dto.RegistrationDetailsForMemberDto;
import webly.meyzieu_gym.back.usermanagement.user.dto.GuardianDto;

@Service
public class MemberAdminService {
        private final MemberRepository memberRepository;

    public MemberAdminService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional(readOnly = true)
    public MemberProfileAdminDto getMemberProfileAdmin(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        List<GuardianDto> guardians = member.getGuardians().stream()
                .map(guardian -> new GuardianDto(
                        guardian.getUser().getId(),
                        guardian.getUser().getFirstname(),
                        guardian.getUser().getLastname()))
                .collect(Collectors.toList());

        List<RegistrationDetailsForMemberDto> registrations = member.getRegistrations().stream()
                .map(reg -> new RegistrationDetailsForMemberDto(
                        reg.getId(),
                        reg.getCourse().getId(),
                        reg.getCourse().getCourseName(),
                        reg.getCourse().getSeason().getId(),
                        reg.getCourse().getSeason().getStartDate(),
                        reg.getCourse().getSeason().getEndDate()
                ))
                .collect(Collectors.toList());

        return new MemberProfileAdminDto(
                member.getId(),
                member.getFirstname(),
                member.getLastname(),
                member.isAllowedToLeave(),
                member.isFirstAidApproved(),
                member.isPhotoApproved(),
                member.isTransportApproved(),
                member.getProfilePictureUrl(),
                member.getSportPassUrl(),
                member.getRegionPassUrl(),
                guardians,
                registrations
        );
    }
}
