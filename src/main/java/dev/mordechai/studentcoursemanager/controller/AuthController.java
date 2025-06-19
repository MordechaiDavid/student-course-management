package dev.mordechai.studentcoursemanager.controller;

import dev.mordechai.studentcoursemanager.dto.request.admin.AdminLoginRequest;
import dev.mordechai.studentcoursemanager.dto.request.student.StudentLoginRequest;
import dev.mordechai.studentcoursemanager.dto.response.LoginResponse;
import dev.mordechai.studentcoursemanager.entity.Session;
import dev.mordechai.studentcoursemanager.response.ApiResponse;
import dev.mordechai.studentcoursemanager.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/admin/login")
    public ResponseEntity<ApiResponse<LoginResponse>> adminLogin(
            @Valid @RequestBody AdminLoginRequest request) {
        Session session = authService.
                authenticateAdminAndGenerateSessionKey(request.getEmail(), request.getPassword());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(LoginResponse.fromEntity(session)));
    }

    @PostMapping("/student/login")
    public ResponseEntity<ApiResponse<LoginResponse>> studentLogin(
            @Valid @RequestBody StudentLoginRequest request) {
        Session session = authService.authenticateStudentAndGenerateSessionKey(request.getSpecialKey());

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(LoginResponse.fromEntity(session)));
    }


}
