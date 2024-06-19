package webly.meyzieu_gym.back.coursemanagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import webly.meyzieu_gym.back.coursemanagement.dto.CourseDto;
import webly.meyzieu_gym.back.coursemanagement.service.CourseService;
import webly.meyzieu_gym.back.security.service.UserDetailsImpl;

@RestController
@RequestMapping("/api")
@PreAuthorize("hasRole('GUARDIAN')")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/members/{memberId}/available-courses")
    @PreAuthorize("@memberOwnershipService.isMemberOwner(#memberId, authentication.principal.id)")
    public ResponseEntity<List<CourseDto>> getAvailableCoursesForMember(@PathVariable Long memberId, Authentication authentication) {
        List<CourseDto> courses = courseService.getAvailableCoursesForMemberId(memberId, getCurrentAuthenticatedUserId());
        return ResponseEntity.ok(courses);
    }

    private Long getCurrentAuthenticatedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long authenticatedUserId = userDetails.getId();
        return authenticatedUserId;
    }
}
