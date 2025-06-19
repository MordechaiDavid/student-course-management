package dev.mordechai.studentcoursemanager.exception.business;

import dev.mordechai.studentcoursemanager.exception.BaseAppException;
import org.springframework.http.HttpStatus;

import java.util.List;

public class CourseCapacityExceededException extends BaseAppException {
    public CourseCapacityExceededException(){
        super(HttpStatus.CONFLICT, List.of("Can't add more students – course is full"));
    }
}
