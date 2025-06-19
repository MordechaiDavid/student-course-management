package dev.mordechai.studentcoursemanager.repository;

import dev.mordechai.studentcoursemanager.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findBySecretKey(String specialKey);

    boolean existsBySecretKey(String specialKey);

    boolean existsByEmail(String email);

    Optional<Student> findByEmail(String email);
}