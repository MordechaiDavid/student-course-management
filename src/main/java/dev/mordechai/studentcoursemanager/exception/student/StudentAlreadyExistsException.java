package dev.mordechai.studentcoursemanager.exception.student;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class StudentAlreadyExistsException extends RuntimeException {

    public StudentAlreadyExistsException() {
        super("Student with this email already exists");
    }

    public StudentAlreadyExistsException(String message) {
        super(message);
    }
} 