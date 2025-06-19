package dev.mordechai.studentcoursemanager.dto.request.enrollment;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class UnenrollmentRequest {
    @Positive(message = "Student ID must be positive")
    @NotNull(message = "Student ID is required")
    private Long studentId;
    @Positive(message = "Course ID must be positive")
    @NotNull(message = "CourseID is required")
    private Long courseId;
}
