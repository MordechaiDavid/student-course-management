package dev.mordechai.studentcoursemanager.dto.course;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseDTO {
    private Long id;
    private String name;
    private String description;
}
