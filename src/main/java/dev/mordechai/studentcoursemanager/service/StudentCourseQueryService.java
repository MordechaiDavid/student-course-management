package dev.mordechai.studentcoursemanager.service;


import dev.mordechai.studentcoursemanager.dto.entity.course.CourseWithStudentsDTO;
import dev.mordechai.studentcoursemanager.dto.entity.student.StudentWithCoursesDTO;

import java.util.List;

public interface StudentCourseQueryService {
    List<StudentWithCoursesDTO> getAllStudentsWithCourses();
    List<CourseWithStudentsDTO> getAllCoursesWithStudents();
}
