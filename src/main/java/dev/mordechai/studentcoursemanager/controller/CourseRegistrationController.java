package dev.mordechai.studentcoursemanager.controller;

import dev.mordechai.studentcoursemanager.dto.request.CourseRegisterRequest;
import dev.mordechai.studentcoursemanager.dto.request.CourseUnregisterRequest;
import dev.mordechai.studentcoursemanager.dto.response.CourseRegisterResponse;
import dev.mordechai.studentcoursemanager.entity.CourseRegistration;
import dev.mordechai.studentcoursemanager.response.ApiResponse;
import dev.mordechai.studentcoursemanager.service.CourseRegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student/courses")
public class CourseRegistrationController {

    private final CourseRegistrationService service;

    @Autowired
    public CourseRegistrationController(CourseRegistrationService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<CourseRegisterResponse>> register
            (@Valid @RequestBody CourseRegisterRequest request){
        CourseRegistration courseRegistration = service.create(CourseRegistration.fromDto(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(CourseRegisterResponse.fromEntity(courseRegistration)));
    }

    @DeleteMapping("/unregister")
    public ResponseEntity<ApiResponse<String>> cancelRegistration(
            @RequestBody CourseUnregisterRequest request){
        service.delete(request.getStudentId(), request.getCourseId());
        ApiResponse<String> response = new ApiResponse<>(
                true,
                "Unregister successfully",
                null
        );
        return ResponseEntity.ok(response);
    }





}
