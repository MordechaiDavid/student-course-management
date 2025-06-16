package dev.mordechai.studentcoursemanager.controller;

import dev.mordechai.studentcoursemanager.dto.request.StudentCreateRequest;
import dev.mordechai.studentcoursemanager.dto.request.StudentUpdateRequest;
import dev.mordechai.studentcoursemanager.service.StudentService;
import dev.mordechai.studentcoursemanager.service.impl.SessionServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final SessionServiceImpl sessionService;
    private final StudentService studentService;

    public StudentController(SessionServiceImpl sessionService, StudentService studentService) {
        this.sessionService = sessionService;
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody StudentCreateRequest request,
                                  @RequestHeader("Session-Key") String sessionKey) {
        // This will throw AccessDeniedException if not admin
        sessionService.validateAdminSession(sessionKey);
        
        // This will throw IllegalArgumentException if email already exists
        if (studentService.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        
        return ResponseEntity.ok(studentService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id,
                               @RequestHeader("Session-Key") String sessionKey) {
        // This will throw AccessDeniedException if not admin
        sessionService.validateAdminSession(sessionKey);
        
        // This will throw IllegalArgumentException if student not found
        var student = studentService.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + id));
            
        return ResponseEntity.ok(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                  @Valid @RequestBody StudentUpdateRequest request,
                                  @RequestHeader("Session-Key") String sessionKey) {
        // This will throw AccessDeniedException if not admin
        sessionService.validateAdminSession(sessionKey);
        
        // This will throw IllegalArgumentException if student not found
        if (!studentService.existsById(id)) {
            throw new IllegalArgumentException("Student not found with id: " + id);
        }
        
        return ResponseEntity.ok(studentService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, 
                                  @RequestHeader("Session-Key") String sessionKey) {
        // This will throw AccessDeniedException if not admin
        sessionService.validateAdminSession(sessionKey);
        
        // This will throw IllegalArgumentException if student not found
        if (!studentService.existsById(id)) {
            throw new IllegalArgumentException("Student not found with id: " + id);
        }
        
        studentService.delete(id);
        return ResponseEntity.ok().build();
    }
}
