package dev.mordechai.studentcoursemanager.service.impl;

import dev.mordechai.studentcoursemanager.dto.admin.CourseStudentData;
import dev.mordechai.studentcoursemanager.dto.admin.StudentCourseData;
import dev.mordechai.studentcoursemanager.entity.Course;
import dev.mordechai.studentcoursemanager.entity.CourseRegistration;
import dev.mordechai.studentcoursemanager.entity.Student;
import dev.mordechai.studentcoursemanager.service.CourseRegistrationService;
import dev.mordechai.studentcoursemanager.service.CourseService;
import dev.mordechai.studentcoursemanager.service.StudentCourseQueryService;
import dev.mordechai.studentcoursemanager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class StudentCourseQueryServiceImpl implements StudentCourseQueryService {

    private final StudentService studentService;
    private final CourseService courseService;
    private final CourseRegistrationService registrationService;

    @Autowired
    public StudentCourseQueryServiceImpl(StudentService studentService, CourseService courseService, CourseRegistrationService registrationService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.registrationService = registrationService;
    }

    @Override
    public List<StudentCourseData> getStudentsWithCourseData() {
        List<StudentCourseData> studentCourseDataList = new ArrayList<>();
        List<Student> students = studentService.getAll();
        for (Student student : students) {
            studentCourseDataList.add(StudentCourseData.builder()
                    .id(student.getId())
                    .name(student.getName()).
                    email(student.getEmail()).
                    courses(new ArrayList<>()).build());
        }
        List<CourseRegistration> registrations = registrationService.getAll();
        for (CourseRegistration registration : registrations) {
            for (StudentCourseData studentCourseData : studentCourseDataList) {
                if (studentCourseData.getId().equals(registration.getId())) {
                    studentCourseData.getCourses().add(Course.builder().id(registration.getCourseId()).build());
                }
            }
        }

        return studentCourseDataList;

    }

    @Override
    public List<CourseStudentData> getCoursesWithStudentData() {
        return List.of();
    }
}
