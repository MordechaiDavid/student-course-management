package dev.mordechai.studentcoursemanager.repository;

import dev.mordechai.studentcoursemanager.dto.flat.StudentCourseFlatDTO;
import dev.mordechai.studentcoursemanager.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findBySpecialKey(String specialKey);

    boolean existsBySpecialKey(String specialKey);

    boolean existsByEmail(String email);

}