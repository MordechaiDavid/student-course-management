package dev.mordechai.studentcoursemanager.service;

import dev.mordechai.studentcoursemanager.entity.Session;
import dev.mordechai.studentcoursemanager.entity.UserType;

import java.util.Optional;

public interface SessionService {
    Session createSession(Long userId, UserType userType);

    Optional<Session> getBySessionKey(String sessionKey);

    Session validateSession(String sessionKey);

    Session validateAdminSession(String sessionKey);

    Session validateStudentSession(String sessionKey);

    boolean existBySessionKey(String sessionKey);

    void deleteBySessionKey(String sessionKey);
}

