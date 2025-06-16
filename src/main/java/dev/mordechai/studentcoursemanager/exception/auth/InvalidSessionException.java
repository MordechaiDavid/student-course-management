package dev.mordechai.studentcoursemanager.exception.auth;

import dev.mordechai.studentcoursemanager.exception.base.BaseException;
import org.springframework.http.HttpStatus;

public class InvalidSessionException extends BaseException {
    public InvalidSessionException() {
        super(HttpStatus.UNAUTHORIZED, "AUTH_004", "Invalid session key");
    }
} 