package webly.meyzieu_gym.back.membermanagement.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import webly.meyzieu_gym.back.membermanagement.dto.CreateMemberDto;
import webly.meyzieu_gym.back.membermanagement.dto.MemberListDto;
import webly.meyzieu_gym.back.membermanagement.service.MemberService;
import webly.meyzieu_gym.back.membermanagement.service.MembersByUserService;
import webly.meyzieu_gym.back.security.service.UserDetailsImpl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasRole('GUARDIAN')")
public class MemberController {

    private final MemberService memberService;
    private final MembersByUserService membersByUserService;

    public MemberController(MemberService memberService, MembersByUserService membersByUserService) {
        this.memberService = memberService;
        this.membersByUserService = membersByUserService;
    }

    @PostMapping("/{id}/members")
    public ResponseEntity<Long> createMember(@Valid @RequestBody CreateMemberDto memberDto, @PathVariable Long id, Authentication authentication) {
        validateUser(id, authentication);
        Long memberId = memberService.createMember(memberDto, id);
        return ResponseEntity.ok(memberId);
    }

    @PreAuthorize("#id == authentication.principal.id")
    @GetMapping("/{id}/members")
    public List<MemberListDto> getMembersByUserId(@PathVariable Long id,
                                                  @RequestParam(required = false, defaultValue = "false") Boolean forRegistration) {
        return membersByUserService.getMembersByUserId(id, forRegistration);
    }

    // Necessary? must verify
    private void validateUser(Long userId, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long authenticatedUserId = userDetails.getId();

        if (!authenticatedUserId.equals(userId)) {
            throw new AccessDeniedException("You do not have permission to perform this action.");
        }
    }
}
