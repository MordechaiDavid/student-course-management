package dev.mordechai.studentcoursemanager.service;

import dev.mordechai.studentcoursemanager.entity.Enrollment;

import java.util.List;

public interface EnrollmentService {
    Enrollment create(Enrollment enrollment);
    List<Enrollment> getAll();
    void delete(Long studentId, Long courseId);
}
