package dev.mordechai.studentcoursemanager.controller;

import dev.mordechai.studentcoursemanager.dto.request.CourseCreateRequest;
import dev.mordechai.studentcoursemanager.dto.request.CourseUpdateRequest;
import dev.mordechai.studentcoursemanager.dto.response.CourseResponse;
import dev.mordechai.studentcoursemanager.entity.Course;
import dev.mordechai.studentcoursemanager.service.impl.CourseServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseServiceImpl courseService;

    @Autowired
    public CourseController(CourseServiceImpl courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public CourseResponse create(@Valid @RequestBody CourseCreateRequest request) {
        Course course = courseService.create(Course.fromDto(request));
        return CourseResponse.fromEntity(course);
    }

    @GetMapping("/{id}")
    public CourseResponse getById(@PathVariable("id") Long id) {
        return CourseResponse.fromEntity(courseService.getById(id));
    }

    @PutMapping("/{id}")
    public CourseResponse update(@PathVariable("id") Long id, @Valid @RequestBody CourseUpdateRequest request) {
        return CourseResponse.fromEntity(courseService.update(id, Course.fromDto(request)));
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        courseService.delete(id);
        return "deleted";
    }
}
