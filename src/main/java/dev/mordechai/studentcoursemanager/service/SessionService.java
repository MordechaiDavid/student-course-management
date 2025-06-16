package dev.mordechai.studentcoursemanager.service;

import dev.mordechai.studentcoursemanager.entity.Session;
import dev.mordechai.studentcoursemanager.entity.UserType;
import dev.mordechai.studentcoursemanager.repository.SessionRepository;
import org.springframework.stereotype.Service;

@Service
public class SessionService {


    private SessionRepository sessionRepository;

    public Session validateSession(String sessionKey) {
        Session session = sessionRepository.findBySessionKey(sessionKey)
                .orElseThrow(() -> new UnauthorizedException("session key invalid or expired"));
        return session;
    }

    public Session validateAdminSession(String sessionKey) {
        Session session = validateSession(sessionKey);
        if (!isAdmin(session.getUserType())) {
            throw new UnauthorizedException("Admin access required");
        }
        return session;
    }

    public Session validateStudentSession(String sessionKey) {
        Session session = validateSession(sessionKey);
        if (isAdmin(session.getUserType())) {
            throw new UnauthorizedException("Student access required");
        }
        return session;
    }

    public boolean isAdmin(UserType userType) {
        return userType.equals(UserType.ADMIN);
    }




}
