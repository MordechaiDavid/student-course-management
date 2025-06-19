package dev.mordechai.studentcoursemanager.exception.auth;

import dev.mordechai.studentcoursemanager.exception.BaseAppException;
import org.springframework.http.HttpStatus;

import java.util.List;

public class InvalidCredentialsException extends BaseAppException {
    public InvalidCredentialsException(String message){
        super(HttpStatus.BAD_REQUEST, List.of(message));
    }
}
