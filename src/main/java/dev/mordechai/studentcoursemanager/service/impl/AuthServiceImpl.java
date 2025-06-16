package dev.mordechai.studentcoursemanager.service.impl;

import dev.mordechai.studentcoursemanager.entity.Admin;
import dev.mordechai.studentcoursemanager.entity.Session;
import dev.mordechai.studentcoursemanager.entity.UserType;
import dev.mordechai.studentcoursemanager.exception.auth.InvalidCredentialsException;
import dev.mordechai.studentcoursemanager.exception.auth.InvalidSessionException;
import dev.mordechai.studentcoursemanager.exception.auth.SessionExpiredException;
import dev.mordechai.studentcoursemanager.repository.AdminRepository;
import dev.mordechai.studentcoursemanager.repository.SessionRepository;
import dev.mordechai.studentcoursemanager.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    private final AdminRepository adminRepository;
    private final SessionRepository sessionRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(
            AdminRepository adminRepository,
            SessionRepository sessionRepository,
            PasswordEncoder passwordEncoder)
    {
        this.adminRepository = adminRepository;
        this.sessionRepository = sessionRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Session authenticateAdmin(String email, String password) {
        Admin admin = adminRepository.findByEmail(email)
                .orElseThrow(InvalidCredentialsException::new);

        if (!passwordEncoder.matches(password, admin.getHashPassword())) {
            throw new InvalidCredentialsException();
        }

        return createSession(admin.getId(), "ADMIN");
    }

    @Override
    public Session authenticateStudent(String specialKey) {
        // TODO: Implement student authentication
        return null;
    }

    @Override
    public void logout(String sessionKey) {
        if (!sessionRepository.existsBySessionKey(sessionKey)) {
            throw new InvalidSessionException();
        }
        sessionRepository.deleteBySessionKey(sessionKey);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean validateSession(String sessionKey) {
        return sessionRepository.findBySessionKey(sessionKey)
                .map(session -> {
                    if (session.getExpiresAt().isBefore(LocalDateTime.now())) {
                        throw new SessionExpiredException();
                    }
                    return true;
                })
                .orElseThrow(InvalidSessionException::new);
    }

    private Session createSession(Long userId, String userType) {
        String sessionKey = UUID.randomUUID().toString();
        LocalDateTime expiresAt = LocalDateTime.now().plusHours(1);

        Session session = new Session();
        session.setSessionKey(sessionKey);
        session.setUserId(userId);
        session.setUserType(UserType.valueOf(userType));
        session.setExpiresAt(expiresAt);
        session.setCreatedAt(LocalDateTime.now());

        return sessionRepository.save(session);
    }
} 