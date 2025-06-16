package dev.mordechai.studentcoursemanager.controller;

import dev.mordechai.studentcoursemanager.dto.request.CourseRegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses/registration")
public class CourseRegistrationController {

    @PostMapping
    public ResponseEntity<?> register(@RequestBody CourseRegisterRequest request,
                                      @RequestHeader("Session-Key") String sessionKey){
        // validate student
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelRegistration(@PathVariable Long id,
                                                @RequestHeader("Session-Key") String sessionKey){
        // validate student
        return null;
    }





}
