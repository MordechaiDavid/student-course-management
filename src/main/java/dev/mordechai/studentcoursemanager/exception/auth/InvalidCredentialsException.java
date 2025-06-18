package dev.mordechai.studentcoursemanager.exception.auth;

import dev.mordechai.studentcoursemanager.exception.FatherAppException;
import org.springframework.http.HttpStatus;

import java.util.List;

public class InvalidCredentialsException extends FatherAppException {
    public InvalidCredentialsException(){
        super(HttpStatus.BAD_REQUEST, List.of(""));
    }
}
