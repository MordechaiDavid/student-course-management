package dev.mordechai.studentcoursemanager.dto.entity.student;

import dev.mordechai.studentcoursemanager.dto.entity.course.CourseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentWithCoursesDTO {
    private Long id;
    private String name;
    private String email;
    private List<CourseDTO> courses;
}