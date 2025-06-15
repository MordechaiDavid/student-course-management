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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AdminServiceImpl adminService;
    private final SessionServiceImpl sessionService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(
            AdminServiceImpl adminService,
            SessionServiceImpl sessionService,
            PasswordEncoder passwordEncoder) {
        this.adminService = adminService;
        this.sessionService = sessionService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Session authenticateAdminAndGenerateSessionKey(String email, String password) {
        Admin admin = adminService.getByEmail(email)
                .orElseThrow(InvalidCredentialsException::new);
        if (!passwordEncoder.matches(password, admin.getHashPassword())) {
            throw new InvalidCredentialsException();
        }
        log.info("Login Admin with id {}", admin.getId());
        return sessionService.createSession(admin.getId(), UserType.ADMIN);
    }

    @Override
    public Session authenticateStudent(String specialKey) {
        // TODO: Implement student authentication
        return null;
    }

    @Override
    public void logout(String sessionKey) {
        if (!sessionService.existBySessionKey(sessionKey)) {
            throw new InvalidSessionException();
        }
        sessionService.deleteBySessionKey(sessionKey);
    }


}