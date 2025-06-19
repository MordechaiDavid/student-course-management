package dev.mordechai.studentcoursemanager.controller;

import dev.mordechai.studentcoursemanager.dto.request.course.StudentCreateRequest;
import dev.mordechai.studentcoursemanager.dto.request.StudentUpdateRequest;
import dev.mordechai.studentcoursemanager.dto.student.StudentResponse;
import dev.mordechai.studentcoursemanager.entity.Student;
import dev.mordechai.studentcoursemanager.response.ApiResponse;
import dev.mordechai.studentcoursemanager.service.StudentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/students")
@Validated
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping
    public ResponseEntity<ApiResponse<StudentResponse>> create(@Valid @RequestBody StudentCreateRequest request) {
        Student student = studentService.create(Student.fromDto(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ApiResponse<>(StudentResponse.fromEntity(student)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<StudentResponse>>> getAll(){
        return ResponseEntity.ok()
                .body(new ApiResponse<>(studentService.getAll().stream()
                        .map(StudentResponse::fromEntity)
                        .toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponse>> getById(
            @PathVariable("id") @Positive(message = "Student ID must be positive")Long id) {
        return ResponseEntity
                .ok(new ApiResponse<>(StudentResponse.fromEntity(studentService.getById(id))));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponse<StudentResponse>> getByEmail(
            @PathVariable("email") @Email(message = "Invalid email format") String email) {
        return ResponseEntity
                .ok(new ApiResponse<>(StudentResponse.fromEntity(studentService.getByEmail(email))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponse>> update(
            @PathVariable("id") @Positive Long id, @Valid @RequestBody StudentUpdateRequest request) {
        Student updatedStudent = studentService.update(id, Student.fromDto(request));
        return ResponseEntity.ok()
                        .body(new ApiResponse<>(StudentResponse.fromEntity(updatedStudent)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(
            @PathVariable ("id") @Positive(message = "Student ID must be positive") Long id) {
        studentService.delete(id);
        ApiResponse<String> response = new ApiResponse<>(
                true,
                "Student deleted successfully",
                null
        );
        return ResponseEntity.ok(response);
    }

}
