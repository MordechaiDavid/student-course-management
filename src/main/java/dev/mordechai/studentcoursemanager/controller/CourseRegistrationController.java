package dev.mordechai.studentcoursemanager.controller;

import dev.mordechai.studentcoursemanager.dto.request.CourseRegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses/registration")
public class CourseRegistrationController {

    @PostMapping
    public ResponseEntity<?> register(@RequestBody CourseRegisterRequest request){
        // validate student
        return null;
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<?> cancelRegistration(@PathVariable Long id){
        // validate student
        return null;
    }





}
