package dev.mordechai.studentcoursemanager.dto.admin;

import dev.mordechai.studentcoursemanager.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentCourseData {
    private Long id;
    private String name;
    private String email;
    List<Course> courses;
}
