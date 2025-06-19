package dev.mordechai.studentcoursemanager.dto.request.course;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseUpdateRequest {
    @NotBlank(message = "Course name is required")
    private String name;
    private String description;
}
