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
import java.util.Optional;
import java.util.UUID;

@Service
public class SessionServiceImpl implements SessionService {

    private final SessionRepository repository;

    @Autowired
    public SessionServiceImpl(SessionRepository sessionRepository) {
        this.repository = sessionRepository;
    }

    @Override
    public Session createSession(Long userId, UserType userType) {
        String sessionKey = UUID.randomUUID().toString();
        LocalDateTime expiresAt = LocalDateTime.now().plusHours(1);

        Session session = new Session();
        session.setSessionKey(sessionKey);
        session.setUserId(userId);
        session.setUserType(userType);
        session.setExpiresAt(expiresAt);
        session.setCreatedAt(LocalDateTime.now());

        return repository.save(session);
    }

    @Override
    public Optional<Session> getBySessionKey(String sessionKey) {
        return repository.findBySessionKey(sessionKey);
    }

    @Override
    public Session validateSession(String sessionKey) {
        Session session = repository.findBySessionKey(sessionKey)
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
    public boolean existBySessionKey(String sessionKey) {
        return repository.existsBySessionKey(sessionKey);
    }

    @Override
    public void deleteBySessionKey(String sessionKey) {
        repository.deleteBySessionKey(sessionKey);
    }

    public boolean isAdmin(UserType userType) {
        return userType.equals(UserType.ADMIN);
    }
}
