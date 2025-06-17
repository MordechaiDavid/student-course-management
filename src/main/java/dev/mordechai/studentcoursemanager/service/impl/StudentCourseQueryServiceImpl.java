package dev.mordechai.studentcoursemanager.service.impl;


import dev.mordechai.studentcoursemanager.dto.course.CourseDTO;
import dev.mordechai.studentcoursemanager.dto.course.CourseWithStudentsDTO;
import dev.mordechai.studentcoursemanager.dto.flat.StudentCourseFlatDTO;
import dev.mordechai.studentcoursemanager.dto.student.StudentDTO;
import dev.mordechai.studentcoursemanager.dto.student.StudentWithCoursesDTO;
import dev.mordechai.studentcoursemanager.repository.StudentCourseQueryRepository;
import dev.mordechai.studentcoursemanager.service.StudentCourseQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentCourseQueryServiceImpl implements StudentCourseQueryService {

    StudentCourseQueryRepository repository;

    @Autowired
    public StudentCourseQueryServiceImpl(StudentCourseQueryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<StudentWithCoursesDTO> getAllStudentsWithCourses() {
        List<StudentCourseFlatDTO> flatList = repository.findAllStudentsWithCourses();
        // Group by student, collect courses
        Map<Long, StudentWithCoursesDTO> studentsMap = new LinkedHashMap<>();
        for (StudentCourseFlatDTO flat : flatList) {
            studentsMap.computeIfAbsent(flat.getStudentId(), id ->
                    new StudentWithCoursesDTO(id, flat.getStudentName(), flat.getStudentEmail(), new ArrayList<>())
            );
            if (flat.getCourseId() != null) {
                studentsMap.get(flat.getStudentId()).getCourses().add(
                        new CourseDTO(flat.getCourseId(), flat.getCourseName(), flat.getCourseDescription())
                );
            }
        }
        return new ArrayList<>(studentsMap.values());
    }

    @Override
    public List<CourseWithStudentsDTO> getAllCoursesWithStudents() {
        List<StudentCourseFlatDTO> flatList = repository.findAllStudentsWithCourses();
        // Group by course, collect students
        Map<Long, CourseWithStudentsDTO> coursesMap = new LinkedHashMap<>();
        for (StudentCourseFlatDTO flat : flatList) {
            if (flat.getCourseId() == null) {
                // Skip courses that don't exist in this flat row
                continue;
            }
            coursesMap.computeIfAbsent(flat.getCourseId(), id ->
                    new CourseWithStudentsDTO(id, flat.getCourseName(), flat.getCourseDescription(), new ArrayList<>())
            );
            if (flat.getStudentId() != null) {
                coursesMap.get(flat.getCourseId()).getStudents().add(
                        new StudentDTO(flat.getStudentId(), flat.getStudentName(), flat.getStudentEmail())
                );
            }
        }
        return new ArrayList<>(coursesMap.values());
    }
}
