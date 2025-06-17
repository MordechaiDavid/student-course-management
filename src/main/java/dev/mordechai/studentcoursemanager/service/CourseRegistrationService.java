package dev.mordechai.studentcoursemanager.service;

import dev.mordechai.studentcoursemanager.entity.CourseRegistration;

import java.util.List;

public interface CourseRegistrationService {
    CourseRegistration create(CourseRegistration courseRegistration);
    List<CourseRegistration> getAll();
    void delete(Long studentId, Long courseId);
}
