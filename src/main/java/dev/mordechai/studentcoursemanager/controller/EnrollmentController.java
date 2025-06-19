package dev.mordechai.studentcoursemanager.controller;

import dev.mordechai.studentcoursemanager.dto.request.enrollment.EnrollmentRequest;
import dev.mordechai.studentcoursemanager.dto.request.enrollment.UnenrollmentRequest;
import dev.mordechai.studentcoursemanager.dto.response.EnrollmentResponse;
import dev.mordechai.studentcoursemanager.entity.Enrollment;
import dev.mordechai.studentcoursemanager.response.ApiResponse;
import dev.mordechai.studentcoursemanager.service.EnrollmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student/courses")
public class EnrollmentController {

    private final EnrollmentService service;

    @Autowired
    public EnrollmentController(EnrollmentService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<EnrollmentResponse>> register
            (@Valid @RequestBody EnrollmentRequest request){
        Enrollment enrollment = service.create(Enrollment.fromDto(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(EnrollmentResponse.fromEntity(enrollment)));
    }

    @DeleteMapping("/unregister")
    public ResponseEntity<ApiResponse<String>> cancelRegistration(
            @RequestBody UnenrollmentRequest request){
        service.delete(request.getStudentId(), request.getCourseId());
        ApiResponse<String> response = new ApiResponse<>(
                true,
                "Unregister successfully",
                null
        );
        return ResponseEntity.ok(response);
    }





}
