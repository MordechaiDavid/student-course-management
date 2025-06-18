package dev.mordechai.studentcoursemanager.service.impl;

import dev.mordechai.studentcoursemanager.entity.Admin;
import dev.mordechai.studentcoursemanager.entity.Session;
import dev.mordechai.studentcoursemanager.entity.Student;
import dev.mordechai.studentcoursemanager.entity.UserType;
import dev.mordechai.studentcoursemanager.exception.auth.InvalidCredentialsException;
import dev.mordechai.studentcoursemanager.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AdminServiceImpl adminService;
    private final StudentServiceImpl studentService;
    private final SessionServiceImpl sessionService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(
            AdminServiceImpl adminService, StudentServiceImpl studentService,
            SessionServiceImpl sessionService,
            PasswordEncoder passwordEncoder) {
        this.adminService = adminService;
        this.studentService = studentService;
        this.sessionService = sessionService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Session authenticateAdminAndGenerateSessionKey(String email, String password) {
        Admin admin = adminService.getByEmail(email)
                .orElseThrow(()-> new InvalidCredentialsException());
        if (!passwordEncoder.matches(password, admin.getHashPassword())) {
            throw new InvalidCredentialsException();
        }
        log.info("Login Admin with email {}", admin.getEmail());
        return sessionService.createSession(admin.getId(), UserType.ADMIN);
    }

    @Override
    public Session authenticateStudentAndGenerateSessionKey(String specialKey) {
        Student student = studentService.getBySpecialKey(specialKey)
                .orElseThrow(RuntimeException::new);
        log.info("Login Student with email {}", student.getEmail());
        return sessionService.createSession(student.getId(), UserType.STUDENT);
    }

    @Override
    public void logout(String sessionKey) {
        if (!sessionService.existBySessionKey(sessionKey)) {
            throw new RuntimeException();
        }
        sessionService.deleteBySessionKey(sessionKey);
    }


}