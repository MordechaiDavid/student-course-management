package dev.mordechai.studentcoursemanager.controller;

import dev.mordechai.studentcoursemanager.service.SessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/dashboard")
public class AdminDashboardController {

    private final SessionService sessionService;

    public AdminDashboardController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping("/students")
    public ResponseEntity<?> getAllStudentsWithCourses(@RequestHeader("Session-Key") String sessionKey) {
        sessionService.validateAdminSession(sessionKey);
        // Return list of students with their registered courses
        // Response should include student details and associated courses
        return null;
    }

    @GetMapping("/courses")
    public ResponseEntity<?> getAllCoursesWithStudents(@RequestHeader("Session-Key") String sessionKey) {
        sessionService.validateAdminSession(sessionKey);
        // Return list of courses with their registered students
        // Response should include course details and enrolled students
        return null;
    }
}
