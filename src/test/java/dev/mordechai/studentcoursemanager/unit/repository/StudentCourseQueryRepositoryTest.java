package dev.mordechai.studentcoursemanager.unit.repository;


import dev.mordechai.studentcoursemanager.dto.flat.StudentCourseFlatDTO;
import dev.mordechai.studentcoursemanager.entity.Course;
import dev.mordechai.studentcoursemanager.entity.Enrollment;
import dev.mordechai.studentcoursemanager.entity.Student;
import dev.mordechai.studentcoursemanager.repository.StudentCourseQueryRepository;
import dev.mordechai.studentcoursemanager.repository.StudentRepository;
import dev.mordechai.studentcoursemanager.repository.CourseRepository;
import dev.mordechai.studentcoursemanager.repository.EnrollmentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class StudentCourseQueryRepositoryTest {

    @Autowired
    private StudentCourseQueryRepository studentCourseQueryRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Test
    @DisplayName("should return students with their courses")
    void testFindAllStudentsWithCourses() {
        // Create and save students with secretKey
        Student student1 = studentRepository.save(Student.builder()
                .name("Alice")
                .email("alice@example.com")
                .secretKey(UUID.randomUUID().toString())
                .build());
        Student student2 = studentRepository.save(Student.builder()
                .name("Bob")
                .email("bob@example.com")
                .secretKey(UUID.randomUUID().toString())
                .build());

        // Create and save courses
        Course course1 = courseRepository.save(Course.builder()
                .name("Math")
                .description("Math Course")
                .build());
        Course course2 = courseRepository.save(Course.builder()
                .name("Science")
                .description("Science Course")
                .build());

        // Create and save enrollments
        enrollmentRepository.save(Enrollment.builder().studentId(student1.getId()).courseId(course1.getId()).build());
        enrollmentRepository.save(Enrollment.builder().studentId(student1.getId()).courseId(course2.getId()).build());
        enrollmentRepository.save(Enrollment.builder().studentId(student2.getId()).courseId(course1.getId()).build());

        List<StudentCourseFlatDTO> result = studentCourseQueryRepository.findAllStudentsWithCourses();

        assertThat(result).isNotEmpty();
        assertThat(result).anyMatch(dto ->
                dto.getStudentName().equals("Alice")
                        && dto.getCourseName().equals("Math")
                        && dto.getCourseDescription().equals("Math Course")
        );
        assertThat(result).anyMatch(dto ->
                dto.getStudentName().equals("Bob")
                        && dto.getCourseName().equals("Math")
        );
    }
}
