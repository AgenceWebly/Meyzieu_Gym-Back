package webly.meyzieu_gym.back.coursemanagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import webly.meyzieu_gym.back.coursemanagement.dto.CourseDto;
import webly.meyzieu_gym.back.coursemanagement.dto.CreateCourseDto;
import webly.meyzieu_gym.back.coursemanagement.service.CourseAdminService;
import webly.meyzieu_gym.back.coursemanagement.service.CourseCreationService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/admin/courses")
@PreAuthorize("hasRole('ADMIN')")
public class CourseAdminController {

    private final CourseCreationService courseCreationService;
    private final CourseAdminService courseAdminService;

    public CourseAdminController(CourseCreationService courseCreationService, CourseAdminService courseAdminService) {
        this.courseCreationService = courseCreationService;
        this.courseAdminService = courseAdminService;
    }

    @PostMapping
    public ResponseEntity<Void> createCourse(@Valid @RequestBody CreateCourseDto createCourseDto) {
        courseCreationService.createCourse(createCourseDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        List<CourseDto> courses = courseAdminService.getAllCourses();
        return ResponseEntity.ok(courses);
    }
}
