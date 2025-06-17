package dev.mordechai.studentcoursemanager.controller;

import dev.mordechai.studentcoursemanager.dto.course.CourseWithStudentsDTO;
import dev.mordechai.studentcoursemanager.dto.student.StudentWithCoursesDTO;
import dev.mordechai.studentcoursemanager.service.StudentCourseQueryService;
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
    public List<StudentWithCoursesDTO> getAllStudentsWithCourses() {
        return service.getAllStudentsWithCourses();
    }

    @GetMapping("/courses")
    public List<CourseWithStudentsDTO> getAllCoursesWithStudents() {
        return service.getAllCoursesWithStudents();
    }
}
