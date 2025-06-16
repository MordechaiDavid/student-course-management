package dev.mordechai.studentcoursemanager.exception.auth;

import dev.mordechai.studentcoursemanager.exception.base.BaseException;
import org.springframework.http.HttpStatus;

public class SessionExpiredException extends BaseException {
    public SessionExpiredException() {
        super(HttpStatus.UNAUTHORIZED, "AUTH_003", "Session has expired");
    }
} 