package webly.meyzieu_gym.back.coursemanagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import webly.meyzieu_gym.back.coursemanagement.dto.CourseDto;
import webly.meyzieu_gym.back.coursemanagement.service.CourseService;

@RestController
@RequestMapping("/api/courses")
@PreAuthorize("hasRole('GUARDIAN')")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/members/{memberId}/available-courses")
        public ResponseEntity<List<CourseDto>> getAvailableCoursesForMember(@PathVariable Long memberId) {
        List<CourseDto> courses = courseService.getAvailableCoursesForMemberId(memberId);
        return ResponseEntity.ok(courses);
    }
}
