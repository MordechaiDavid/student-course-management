package dev.mordechai.studentcoursemanager.dto.entity.student;

import dev.mordechai.studentcoursemanager.dto.entity.course.CourseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class StudentWithCoursesDTO {
    private Long id;
    private String name;
    private String email;
    private List<CourseDTO> courses;
}