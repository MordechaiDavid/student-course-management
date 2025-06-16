package dev.mordechai.studentcoursemanager.exception.student;

import dev.mordechai.studentcoursemanager.exception.base.BaseException;
import org.springframework.http.HttpStatus;

public class StudentNotFoundException extends BaseException {
    public StudentNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, "STU_001", "Student not found with id: " + id);
    }
} 