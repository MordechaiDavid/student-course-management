package dev.mordechai.studentcoursemanager.controller;

import dev.mordechai.studentcoursemanager.dto.request.course.CourseCreateRequest;
import dev.mordechai.studentcoursemanager.dto.request.course.CourseUpdateRequest;
import dev.mordechai.studentcoursemanager.dto.response.CourseResponse;
import dev.mordechai.studentcoursemanager.dto.student.StudentResponse;
import dev.mordechai.studentcoursemanager.entity.Course;
import dev.mordechai.studentcoursemanager.response.ApiResponse;
import dev.mordechai.studentcoursemanager.service.CourseService;
import dev.mordechai.studentcoursemanager.service.impl.CourseServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/courses")
@Validated
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CourseResponse>> create(
            @Valid @RequestBody CourseCreateRequest request) {
        Course course = courseService.create(Course.fromDto(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(CourseResponse.fromEntity(course)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseResponse>> getById(
            @PathVariable("id") @Positive Long id) {
        return ResponseEntity.ok()
                .body(new ApiResponse<>(CourseResponse.fromEntity(courseService.getById(id))));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CourseResponse>>> getAll() {
        return ResponseEntity.ok()
                .body(new ApiResponse<>(courseService.getAll().stream()
                        .map(CourseResponse::fromEntity)
                        .toList()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseResponse>> update(
            @PathVariable("id") @Positive Long id, @Valid @RequestBody CourseUpdateRequest request) {
        Course updatedCourse = courseService.update(id, Course.fromDto(request));
        return ResponseEntity.ok()
                .body(new ApiResponse<>(CourseResponse.fromEntity(updatedCourse)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(
            @PathVariable("id") @Positive Long id) {
        courseService.delete(id);
        ApiResponse<String> response = new ApiResponse<>(
                true,
                "Course deleted successfully",
                null
        );
        return ResponseEntity.ok(response);
    }
}
