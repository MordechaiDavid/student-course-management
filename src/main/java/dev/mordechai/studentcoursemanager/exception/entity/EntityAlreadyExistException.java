package dev.mordechai.studentcoursemanager.exception.entity;

import dev.mordechai.studentcoursemanager.exception.FatherAppException;
import org.springframework.http.HttpStatus;

import java.util.List;

public class EntityAlreadyExistException extends FatherAppException {
    public EntityAlreadyExistException(String message){
        super(HttpStatus.CONFLICT, List.of(message));
    }
}
