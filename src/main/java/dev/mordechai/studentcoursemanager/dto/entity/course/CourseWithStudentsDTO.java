package dev.mordechai.studentcoursemanager.dto.entity.course;

import dev.mordechai.studentcoursemanager.dto.entity.student.StudentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseWithStudentsDTO {
    private Long id;
    private String name;
    private String description;
    private List<StudentDTO> students;
}
