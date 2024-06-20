package webly.meyzieu_gym.back.coursemanagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import webly.meyzieu_gym.back.coursemanagement.dto.CourseDto;
import webly.meyzieu_gym.back.coursemanagement.dto.CreateCourseDto;
import webly.meyzieu_gym.back.coursemanagement.service.CourseAdminService;
import webly.meyzieu_gym.back.coursemanagement.service.CourseCreationAdminService;
import webly.meyzieu_gym.back.coursemanagement.service.CourseUpdateAdminService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/admin/courses")
@PreAuthorize("hasRole('ADMIN')")
public class CourseAdminController {

    private final CourseCreationAdminService courseCreationService;
    private final CourseAdminService courseAdminService;
    private final CourseUpdateAdminService courseUpdateAdminService;

    public CourseAdminController(CourseCreationAdminService courseCreationService, CourseAdminService courseAdminService, CourseUpdateAdminService courseUpdateAdminService) {
        this.courseCreationService = courseCreationService;
        this.courseAdminService = courseAdminService;
        this.courseUpdateAdminService = courseUpdateAdminService;
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

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable Long id) {
        CourseDto course = courseAdminService.getCourseById(id);
        return ResponseEntity.ok(course);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDto> updateCourse(@PathVariable Long id, @Valid @RequestBody CreateCourseDto updateCourseDto) {
        CourseDto updatedCourse = courseUpdateAdminService.updateCourse(id, updateCourseDto);
        return ResponseEntity.ok(updatedCourse);
    }
}
