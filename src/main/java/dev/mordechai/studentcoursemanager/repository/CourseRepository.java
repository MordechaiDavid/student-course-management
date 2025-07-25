package dev.mordechai.studentcoursemanager.repository;

import dev.mordechai.studentcoursemanager.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    boolean existsByName(String name);
    boolean existsById(Long id);
}