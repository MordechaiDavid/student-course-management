package dev.mordechai.studentcoursemanager.service.impl;

import dev.mordechai.studentcoursemanager.entity.Session;
import dev.mordechai.studentcoursemanager.entity.UserType;
import dev.mordechai.studentcoursemanager.exception.auth.InvalidSessionException;
import dev.mordechai.studentcoursemanager.exception.auth.SessionExpiredException;
import dev.mordechai.studentcoursemanager.repository.SessionRepository;
import dev.mordechai.studentcoursemanager.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;

    @Autowired
    public SessionServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Session validateSession(String sessionKey) {
        Session session = sessionRepository.findBySessionKey(sessionKey)
                .orElseThrow(InvalidSessionException::new);

        if (session.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new SessionExpiredException();
        }

        return session;
    }

    @Override
    public Session validateAdminSession(String sessionKey) {
        Session session = validateSession(sessionKey);
        if (!isAdmin(session.getUserType())) {
            throw new InvalidSessionException();
        }
        return session;
    }

    @Override
    public Session validateStudentSession(String sessionKey) {
        Session session = validateSession(sessionKey);
        if (isAdmin(session.getUserType())) {
            throw new InvalidSessionException();
        }
        return session;
    }

    @Override
    public boolean isAdmin(UserType userType) {
        return userType.equals(UserType.ADMIN);
    }
}
