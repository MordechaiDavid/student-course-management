package dev.mordechai.studentcoursemanager.exception.entity;

import dev.mordechai.studentcoursemanager.exception.BaseAppException;
import org.springframework.http.HttpStatus;

import java.util.List;

public class EntityNotFoundException extends BaseAppException {
    public EntityNotFoundException(String message){
        super(HttpStatus.NOT_FOUND, List.of(message));
    }
}
