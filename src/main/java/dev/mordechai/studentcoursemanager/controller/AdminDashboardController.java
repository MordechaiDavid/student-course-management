package dev.mordechai.studentcoursemanager.controller;

import dev.mordechai.studentcoursemanager.dto.admin.StudentCourseData;
import dev.mordechai.studentcoursemanager.service.SessionService;
import dev.mordechai.studentcoursemanager.service.StudentCourseQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
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
    public List<StudentCourseData> getAllStudentsWithCourses() {
        return service.getStudentsWithCourseData();
    }

    @GetMapping("/courses")
    public ResponseEntity<?> getAllCoursesWithStudents() {
        return null;
    }
}
