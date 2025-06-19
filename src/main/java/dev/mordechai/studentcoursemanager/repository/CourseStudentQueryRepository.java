package dev.mordechai.studentcoursemanager.repository;

import dev.mordechai.studentcoursemanager.dto.flat.StudentCourseFlatDTO;
import dev.mordechai.studentcoursemanager.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseStudentQueryRepository extends JpaRepository<Course, Long> {
    @Query("""
    SELECT new dev.mordechai.studentcoursemanager.dto.flat.StudentCourseFlatDTO(
        s.id, s.name, s.email, c.id, c.name, c.description
    )
    FROM Course c
    LEFT JOIN Enrollment cr ON cr.courseId = c.id
    LEFT JOIN Student s ON cr.studentId = s.id
    """)
    List<StudentCourseFlatDTO> findAllCoursesWithStudents();
}
