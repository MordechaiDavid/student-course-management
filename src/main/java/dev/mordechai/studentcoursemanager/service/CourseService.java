package dev.mordechai.studentcoursemanager.service;


import dev.mordechai.studentcoursemanager.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    Course create(Course course);

    Course getById(Long id);

    List<Course> getAll();

    Course update(Long id, Course course);

    void delete(Long id);

    boolean existById(Long id);
}