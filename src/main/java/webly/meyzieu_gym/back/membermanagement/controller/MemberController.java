package webly.meyzieu_gym.back.membermanagement.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import webly.meyzieu_gym.back.membermanagement.dto.CreateMemberDto;
import webly.meyzieu_gym.back.membermanagement.service.MemberService;
import webly.meyzieu_gym.back.security.service.UserDetailsImpl;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("users/{userId}/members")
    @PreAuthorize("hasRole('GUARDIAN')")
    public ResponseEntity<Long> createMember(@Valid @RequestBody CreateMemberDto memberDto, @PathVariable Long userId, Authentication authentication) {
        validateUser(userId, authentication);
        Long memberId = memberService.createMember(memberDto, userId);
        return ResponseEntity.ok(memberId);
    }

    private void validateUser(Long userId, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long authenticatedUserId = userDetails.getId();

        if (!authenticatedUserId.equals(userId)) {
            throw new AccessDeniedException("You do not have permission to perform this action.");
        }
    }
}
