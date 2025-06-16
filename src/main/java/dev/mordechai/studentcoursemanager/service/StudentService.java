package dev.mordechai.studentcoursemanager.service;

import dev.mordechai.studentcoursemanager.dto.request.StudentCreateRequest;
import dev.mordechai.studentcoursemanager.dto.request.StudentUpdateRequest;
import dev.mordechai.studentcoursemanager.entity.Student;

import java.util.Optional;

public interface StudentService {
    Student create(StudentCreateRequest request);
    Optional<Student> findById(Long id);
    Student update(Long id, StudentUpdateRequest request);
    void delete(Long id);
    boolean existsByEmail(String email);
    boolean existsById(Long id);
} 