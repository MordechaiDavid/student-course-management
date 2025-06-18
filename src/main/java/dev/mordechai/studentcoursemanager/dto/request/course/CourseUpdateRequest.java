package dev.mordechai.studentcoursemanager.dto.request.course;

import lombok.Data;

@Data
public class CourseUpdateRequest {
    private String name;
    private String description;
}
