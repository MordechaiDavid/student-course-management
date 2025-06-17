package dev.mordechai.studentcoursemanager.dto.course;

import dev.mordechai.studentcoursemanager.dto.student.StudentDTO;
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
