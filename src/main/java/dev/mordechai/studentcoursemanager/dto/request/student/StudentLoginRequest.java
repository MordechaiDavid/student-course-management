package dev.mordechai.studentcoursemanager.dto.request.student;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StudentLoginRequest {
    @NotBlank(message = "Special key is required")
    private String specialKey;
} 