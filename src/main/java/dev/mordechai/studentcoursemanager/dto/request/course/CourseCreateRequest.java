package dev.mordechai.studentcoursemanager.dto.request.course;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CourseCreateRequest {
    @NotBlank(message = "Course name is required")
    private String name;
    private String description;
}
