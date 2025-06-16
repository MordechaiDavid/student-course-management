package dev.mordechai.studentcoursemanager.dto.request;

import lombok.Data;

@Data
public class CourseUpdateRequest {
    private String name;
    private String description;
}
