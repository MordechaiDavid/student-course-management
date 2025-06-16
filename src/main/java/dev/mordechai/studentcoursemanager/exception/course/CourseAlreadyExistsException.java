package dev.mordechai.studentcoursemanager.exception.course;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CourseAlreadyExistsException extends RuntimeException {

    public CourseAlreadyExistsException() {
        super("Course with this email already exists");
    }

    public CourseAlreadyExistsException(String message) {
        super(message);
    }
} 