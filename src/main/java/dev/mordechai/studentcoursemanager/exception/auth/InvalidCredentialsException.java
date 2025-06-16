package dev.mordechai.studentcoursemanager.exception.auth;

import dev.mordechai.studentcoursemanager.exception.base.BaseException;
import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends BaseException {
    public InvalidCredentialsException() {
        super(HttpStatus.UNAUTHORIZED, "AUTH_002", "Invalid email or password");
    }
} 