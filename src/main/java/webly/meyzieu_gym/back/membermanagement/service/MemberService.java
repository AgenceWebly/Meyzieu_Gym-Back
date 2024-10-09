package webly.meyzieu_gym.back.membermanagement.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import webly.meyzieu_gym.back.common.exception.custom.MemberNotFoundException;
import webly.meyzieu_gym.back.coursemanagement.dto.TrainingSlotDto;
import webly.meyzieu_gym.back.coursemanagement.entity.TrainingSlot;
import webly.meyzieu_gym.back.membermanagement.dto.*;
import webly.meyzieu_gym.back.membermanagement.entity.Member;
import webly.meyzieu_gym.back.membermanagement.repository.MemberRepository;
import webly.meyzieu_gym.back.registrationmanagement.dto.RegistrationDetailsForCertificateDto;
import webly.meyzieu_gym.back.registrationmanagement.dto.RegistrationDetailsForMemberDto;
import webly.meyzieu_gym.back.usermanagement.user.dto.GuardianDto;

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

    @Transactional(readOnly = true)
    public MemberDetailsDto getMemberDetails(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        List<GuardianDto> guardians = member.getGuardians().stream()
                .map(guardian -> new GuardianDto(
                        guardian.getUser().getId(),
                        guardian.getUser().getFirstname(),
                        guardian.getUser().getLastname()))
                .collect(Collectors.toList());

        List<EmergencyContactDto> emergencyContacts = member.getEmergencyContacts().stream()
                .map(emergencyContact -> new EmergencyContactDto(
                        emergencyContact.getFirstname(),
                        emergencyContact.getLastname(),
                        emergencyContact.getRelationToMember(),
                        emergencyContact.getPhoneNumber()
                ))
                .collect(Collectors.toList());

        List<RegistrationDetailsForCertificateDto> registrations = member.getRegistrations().stream()
                .map(reg -> new RegistrationDetailsForCertificateDto(
                        reg.getCourse().getCourseName(),
                        mapToTrainingSlotDtos(reg.getCourse().getTrainingSlots()),
                        reg.getRegistrationFee(),
                        reg.getCourse().getSeason().getStartDate(),
                        reg.getCourse().getSeason().getEndDate()
                ))
                .collect(Collectors.toList());

        return new MemberDetailsDto(
                member.getId(),
                member.getFirstname(),
                member.getLastname(),
                member.getBirthdate(),
                member.getSchool(),
                member.isAllowedToLeave(),
                member.isFirstAidApproved(),
                member.isPhotoApproved(),
                member.isTransportApproved(),
                member.getProfilePictureUrl(),
                member.getSportPassUrl(),
                member.getRegionPassUrl(),
                guardians,
                registrations,
                emergencyContacts
        );
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

    private List<TrainingSlotDto> mapToTrainingSlotDtos(List<TrainingSlot> trainingSlots) {
        return trainingSlots.stream()
                .map(slot -> new TrainingSlotDto(
                        slot.getId(),
                        slot.getDay(),
                        slot.getStartTime(),
                        slot.getEndTime()))
                .collect(Collectors.toList());
    }
}