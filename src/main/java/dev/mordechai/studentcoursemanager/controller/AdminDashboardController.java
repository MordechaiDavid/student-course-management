package dev.mordechai.studentcoursemanager.controller;

import dev.mordechai.studentcoursemanager.dto.entity.course.CourseWithStudentsDTO;
import dev.mordechai.studentcoursemanager.dto.entity.student.StudentWithCoursesDTO;
import dev.mordechai.studentcoursemanager.response.ApiResponse;
import dev.mordechai.studentcoursemanager.service.StudentCourseQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/dashboard")
public class AdminDashboardController {

    private final StudentCourseQueryService service;

    public AdminDashboardController(StudentCourseQueryService service) {
        this.service = service;
    }

    @GetMapping("/students")
    public ResponseEntity<ApiResponse<List<StudentWithCoursesDTO>>> getAllStudentsWithCourses() {
        return ResponseEntity.ok()
                        .body(new ApiResponse<>(service.getAllStudentsWithCourses()));
    }

    @GetMapping("/courses")
    public ResponseEntity<ApiResponse<List<CourseWithStudentsDTO>>> getAllCoursesWithStudents() {
        return ResponseEntity.ok()
                        .body(new ApiResponse<>(service.getAllCoursesWithStudents()));
    }
}
