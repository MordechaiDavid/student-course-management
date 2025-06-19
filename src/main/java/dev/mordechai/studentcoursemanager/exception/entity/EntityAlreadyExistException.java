package dev.mordechai.studentcoursemanager.exception.entity;

import dev.mordechai.studentcoursemanager.exception.BaseAppException;
import org.springframework.http.HttpStatus;

import java.util.List;

public class EntityAlreadyExistException extends BaseAppException {
    public EntityAlreadyExistException(String message){
        super(HttpStatus.CONFLICT, List.of(message));
    }
}
