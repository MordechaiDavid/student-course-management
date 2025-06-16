package dev.mordechai.studentcoursemanager.controller;

import dev.mordechai.studentcoursemanager.dto.request.AdminLoginRequest;
import dev.mordechai.studentcoursemanager.dto.request.StudentLoginRequest;
import dev.mordechai.studentcoursemanager.dto.response.LoginResponse;
import dev.mordechai.studentcoursemanager.entity.Session;
import dev.mordechai.studentcoursemanager.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<LoginResponse> adminLogin(@Valid @RequestBody AdminLoginRequest request) {
        Session session = authService.authenticateAdmin(request.getEmail(), request.getPassword());

        return ResponseEntity.ok(LoginResponse.builder()
                .sessionKey(session.getSessionKey())
                .userType(session.getUserType().name())
                .userId(session.getUserId())
                .build());
    }

    @PostMapping("/student/login")
    public ResponseEntity<LoginResponse> studentLogin(@Valid @RequestBody StudentLoginRequest request) {
        Session session = authService.authenticateStudent(request.getSpecialKey());

        return ResponseEntity.ok(LoginResponse.builder()
                .sessionKey(session.getSessionKey())
                .userType(session.getUserType().name())
                .userId(session.getUserId())
                .build());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Session-Key") String sessionKey) {
        authService.logout(sessionKey);
        return ResponseEntity.ok().build();
    }
}
