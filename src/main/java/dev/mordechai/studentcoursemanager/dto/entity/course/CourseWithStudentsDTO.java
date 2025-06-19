package dev.mordechai.studentcoursemanager.dto.entity.course;

import dev.mordechai.studentcoursemanager.dto.entity.student.StudentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CourseWithStudentsDTO {
    private Long id;
    private String name;
    private String description;
    private List<StudentDTO> students;
}
