package dev.mordechai.studentcoursemanager.service;

import dev.mordechai.studentcoursemanager.dto.admin.CourseStudentData;
import dev.mordechai.studentcoursemanager.dto.admin.StudentCourseData;

import java.util.List;

public interface StudentCourseQueryService {
    List<StudentCourseData> getStudentsWithCourseData();
    List<CourseStudentData> getCoursesWithStudentData();
}
