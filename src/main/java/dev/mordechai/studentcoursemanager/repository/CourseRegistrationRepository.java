package dev.mordechai.studentcoursemanager.repository;

import dev.mordechai.studentcoursemanager.entity.CourseRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, Long> {
    Optional<CourseRegistration> findByStudentIdAndCourseId(Long studentId, Long courseId);
    List<CourseRegistration> findByStudentId(Long studentId);
    List<CourseRegistration> findByCourseId(Long courseId);

} 