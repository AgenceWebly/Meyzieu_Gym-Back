package webly.meyzieu_gym.back.membermanagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import webly.meyzieu_gym.back.membermanagement.dto.MemberListAdminDto;
import webly.meyzieu_gym.back.membermanagement.dto.MemberProfileAdminDto;
import webly.meyzieu_gym.back.membermanagement.service.MemberAdminService;

@RestController
@RequestMapping("/api/admin/members")
@PreAuthorize("hasRole('ADMIN')")
public class MemberAdminController {

    private final MemberAdminService memberAdminService;

    public MemberAdminController(MemberAdminService memberAdminService) {
        this.memberAdminService = memberAdminService;
    }
    @GetMapping
    public ResponseEntity<List<MemberListAdminDto>> getAllMembers() {
        List<MemberListAdminDto> members = memberAdminService.getAllMembers();
        return ResponseEntity.ok(members);
 
    }
    @GetMapping("/{memberId}")
    public ResponseEntity<MemberProfileAdminDto> getMemberProfileAdmin(@PathVariable Long memberId) {
        MemberProfileAdminDto memberProfileAdminDto = memberAdminService.getMemberProfileAdmin(memberId);
        return ResponseEntity.ok(memberProfileAdminDto);
    }
}
