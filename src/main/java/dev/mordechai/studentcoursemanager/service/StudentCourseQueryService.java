package dev.mordechai.studentcoursemanager.service;


import dev.mordechai.studentcoursemanager.dto.student.StudentWithCoursesDTO;

import java.util.List;

public interface StudentCourseQueryService {
    List<StudentWithCoursesDTO> getAllStudentsWithCourses();
}
