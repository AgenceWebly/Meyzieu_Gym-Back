package webly.meyzieu_gym.back.programmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import webly.meyzieu_gym.back.programmanagement.dto.CourseDto;
import webly.meyzieu_gym.back.programmanagement.service.CourseService;

@RestController
@RequestMapping("/api/courses")
@PreAuthorize("hasRole('ADMIN')")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<Void> createCourse(@Valid @RequestBody CourseDto courseDto) {
        courseService.createCourse(courseDto);
        return ResponseEntity.ok().build();
    }
}
