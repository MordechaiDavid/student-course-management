package dev.mordechai.studentcoursemanager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/admin/login")
    public ResponseEntity<?> adminLogin(@RequestBody adminLoginRequest request){
        // Validate admin credentials
        // Generate and store session
        return null;
    }

    @PostMapping("/student/login")
    public ResponseEntity<?> studentLogin(@RequestBody StudentLoginRequest request) {
        // Validate student special key
        // Generate and store session
        return null;
    }

    // TODO logout


}
