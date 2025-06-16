package dev.mordechai.studentcoursemanager.service;


import dev.mordechai.studentcoursemanager.entity.Course;

import java.util.Optional;

public interface CourseService {
    Course create(Course course);

    Course getById(Long id);

    Course update(Long id, Course course);

    void delete(Long id);
}