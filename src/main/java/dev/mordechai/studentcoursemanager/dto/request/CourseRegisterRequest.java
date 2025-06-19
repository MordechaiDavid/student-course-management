package dev.mordechai.studentcoursemanager.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CourseRegisterRequest {
    @Positive(message = "Student ID must be positive")
    @NotNull(message = "Student ID is required")
    private Long studentId;
    @Positive(message = "Course ID must be positive")
    @NotNull(message = "CourseID is required")
    private Long courseId;
}
