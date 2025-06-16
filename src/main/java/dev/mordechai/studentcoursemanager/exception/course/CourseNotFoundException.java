package dev.mordechai.studentcoursemanager.exception.course;

import dev.mordechai.studentcoursemanager.exception.base.BaseException;
import org.springframework.http.HttpStatus;

public class CourseNotFoundException extends BaseException {
    public CourseNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, "STU_001", "Course not found with id: " + id);
    }
} 