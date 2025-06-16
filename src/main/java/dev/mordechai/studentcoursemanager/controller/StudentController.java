package dev.mordechai.studentcoursemanager.controller;

import dev.mordechai.studentcoursemanager.dto.request.StudentCreateRequest;
import dev.mordechai.studentcoursemanager.dto.request.StudentUpdateRequest;
import dev.mordechai.studentcoursemanager.dto.response.StudentResponse;
import dev.mordechai.studentcoursemanager.entity.Student;
import dev.mordechai.studentcoursemanager.service.impl.StudentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentServiceImpl studentService;

    @Autowired
    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public StudentResponse create(@Valid @RequestBody StudentCreateRequest request) {
        Student student = studentService.create(Student.fromDto(request));
        return StudentResponse.fromEntity(student);
    }

    @GetMapping("/{id}")
    public StudentResponse getById(@PathVariable("id") Long id) {
        return StudentResponse.fromEntity(studentService.getById(id));
    }

    @PutMapping("/{id}")
    public StudentResponse update(@PathVariable("id") Long id, @Valid @RequestBody StudentUpdateRequest request) {
        return StudentResponse.fromEntity(studentService.update(id, Student.fromDto(request)));
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        studentService.delete(id);
        return "deleted";
    }
}
