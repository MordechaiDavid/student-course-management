package dev.mordechai.studentcoursemanager.exception.business;

import dev.mordechai.studentcoursemanager.exception.BaseAppException;
import org.springframework.http.HttpStatus;

import java.util.List;

public class StudentCourseLimitExceededException extends BaseAppException {
    public StudentCourseLimitExceededException(){
        super(HttpStatus.CONFLICT, List.of("Student is trying to register to more than 2 courses"));
    }
}
