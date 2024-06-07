package webly.meyzieu_gym.back.membermanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import webly.meyzieu_gym.back.common.exception.custom.UserNotFoundException;
import webly.meyzieu_gym.back.membermanagement.dto.CreateMemberDto;
import webly.meyzieu_gym.back.membermanagement.dto.EmergencyContactDto;
import webly.meyzieu_gym.back.membermanagement.entity.EmergencyContact;
import webly.meyzieu_gym.back.membermanagement.entity.Member;
import webly.meyzieu_gym.back.membermanagement.entity.MemberGuardian;
import webly.meyzieu_gym.back.membermanagement.repository.EmergencyContactRepository;
import webly.meyzieu_gym.back.membermanagement.repository.MemberGuardianRepository;
import webly.meyzieu_gym.back.membermanagement.repository.MemberRepository;
import webly.meyzieu_gym.back.usermanagement.user.User;
import webly.meyzieu_gym.back.usermanagement.user.UserRepository;

@Service
public class MemberService {

    private UserRepository userRepository;
    private MemberRepository memberRepository;
    private MemberGuardianRepository memberGuardianRepository;
    private EmergencyContactRepository emergencyContactRepository;

    public MemberService(UserRepository userRepository, MemberRepository memberRepository, MemberGuardianRepository memberGuardianRepository, EmergencyContactRepository emergencyContactRepository) {
        this.userRepository = userRepository;
        this.memberRepository = memberRepository;
        this.memberGuardianRepository = memberGuardianRepository;
        this.emergencyContactRepository = emergencyContactRepository;
    }

    @Transactional
    public Long createMember(CreateMemberDto createMemberDto, Long userId) {
        User user = getUserById(userId);
        Member member = saveMember(createMemberDto);
        saveMemberGuardian(member, user, createMemberDto.getRelationToMember());
        saveEmergencyContacts(member, createMemberDto.getEmergencyContacts());
        return member.getId();
    }

    private User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    private Member saveMember(CreateMemberDto createMemberDto) {
        Member member = new Member();
        member.setFirstname(createMemberDto.getFirstname());
        member.setLastname(createMemberDto.getLastname());
        member.setBirthdate(createMemberDto.getBirthdate());
        member.setGender(createMemberDto.getGender());
        member.setSchool(createMemberDto.getSchool());
        member.setPhotoApproved(createMemberDto.isPhotoApproved());
        member.setTransportApproved(createMemberDto.isTransportApproved());
        member.setFirstAidApproved(createMemberDto.isFirstAidApproved());
        member.setAllowedToLeave(createMemberDto.isAllowedToLeave());
        member.setProfilePictureUrl(createMemberDto.getProfilePictureUrl());
        return memberRepository.save(member);
    }

    private void saveMemberGuardian(Member member, User user, String relationToMember) {
        MemberGuardian memberGuardian = new MemberGuardian();
        memberGuardian.setMember(member);
        memberGuardian.setUser(user);
        memberGuardian.setRelationToMember(relationToMember);
        memberGuardian.setReferent(true);
        memberGuardianRepository.save(memberGuardian);
    }

    private void saveEmergencyContacts(Member member, List<EmergencyContactDto> emergencyContacts) {
        for (EmergencyContactDto contactDto : emergencyContacts) {
            EmergencyContact contact = new EmergencyContact();
            contact.setFirstname(contactDto.getFirstname());
            contact.setLastname(contactDto.getLastname());
            contact.setRelationToMember(contactDto.getRelationToMember());
            contact.setPhoneNumber(contactDto.getPhoneNumber());
            contact.setMember(member);
            emergencyContactRepository.save(contact);
        }
    }

}
