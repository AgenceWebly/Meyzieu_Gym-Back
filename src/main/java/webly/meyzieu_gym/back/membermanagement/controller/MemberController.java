package webly.meyzieu_gym.back.membermanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import webly.meyzieu_gym.back.membermanagement.dto.CreateMemberDto;
import webly.meyzieu_gym.back.membermanagement.dto.MemberDto;
import webly.meyzieu_gym.back.membermanagement.dto.MemberListDto;
import webly.meyzieu_gym.back.membermanagement.service.CreateMemberService;
import webly.meyzieu_gym.back.membermanagement.service.MemberService;
import webly.meyzieu_gym.back.membermanagement.service.MembersByUserService;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasRole('GUARDIAN')")
public class MemberController {

    private final CreateMemberService createMemberService;
    private final MembersByUserService membersByUserService;
    private final MemberService memberService;

    public MemberController(CreateMemberService createMemberService, MembersByUserService membersByUserService, MemberService memberService) {
        this.createMemberService = createMemberService;
        this.membersByUserService = membersByUserService;
        this.memberService = memberService;
    }

    @PreAuthorize("#id == authentication.principal.id")
    @PostMapping("/{id}/members")
    public ResponseEntity<Long> createMember(@Valid @RequestBody CreateMemberDto memberDto, @PathVariable Long id) {
        Long memberId = createMemberService.createMember(memberDto, id);
        return ResponseEntity.ok(memberId);
    }

    @PreAuthorize("#id == authentication.principal.id")
    @GetMapping("/{id}/members")
    public List<MemberListDto> getMembersByUserId(@PathVariable Long id,
                                                  @RequestParam(required = false, defaultValue = "false") Boolean forRegistration) {
        return membersByUserService.getMembersByUserId(id, forRegistration);
    }

    @GetMapping("/members/{memberId}")
    @PreAuthorize("@memberOwnershipService.isMemberOwner(#memberId, authentication.principal.id)")
    public ResponseEntity<MemberDto> getMemberById(@PathVariable Long memberId) {
        MemberDto memberDto = memberService.getMemberById(memberId);
        return ResponseEntity.ok(memberDto);
    }
}
