package dev.mordechai.studentcoursemanager.dto.admin;

import dev.mordechai.studentcoursemanager.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseStudentData {

    List<Student> students;
}
