package dev.mordechai.studentcoursemanager.repository;

import dev.mordechai.studentcoursemanager.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseStudentQueryRepository extends JpaRepository<Course, Long> {

}
