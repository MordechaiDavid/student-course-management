package dev.mordechai.studentcoursemanager.exception.auth;

import dev.mordechai.studentcoursemanager.exception.ErrorResponse;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class InvalidCredentialsException extends ErrorResponse {
    public InvalidCredentialsException() {
        super(LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value(),
                "Invalid email or password", null, null );
    }
} 