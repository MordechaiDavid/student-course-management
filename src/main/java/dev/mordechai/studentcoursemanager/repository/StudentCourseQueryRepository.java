package dev.mordechai.studentcoursemanager.repository;

import dev.mordechai.studentcoursemanager.dto.flat.StudentCourseFlatDTO;
import dev.mordechai.studentcoursemanager.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentCourseQueryRepository extends JpaRepository<Student, Long> {

    @Query("""
    SELECT new dev.mordechai.studentcoursemanager.dto.flat.StudentCourseFlatDTO(
        s.id, s.name, s.email, c.id, c.name, c.description
    )
    FROM Student s
    LEFT JOIN Enrollment cr ON cr.studentId = s.id
    LEFT JOIN Course c ON cr.courseId = c.id
""")
    List<StudentCourseFlatDTO> findAllStudentsWithCourses();

}

