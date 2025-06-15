package dev.mordechai.studentcoursemanager.service;

import org.springframework.stereotype.Service;

@Service
public class SessionService {

    public SessionInfo validateSession(String sessionKey) {
        Session session = sessionRepository.findBySessionKey(sessionKey)
                .orElseThrow(() -> new UnauthorizedException("Invalid session"));

        if (session.isExpired()) {
            throw new UnauthorizedException("Session expired");
        }

        return new SessionInfo(session.getUserId(), session.isAdmin());
    }

    public void validateAdminSession(String sessionKey) {
        SessionInfo sessionInfo = validateSession(sessionKey);
        if (!sessionInfo.isAdmin()) {
            throw new UnauthorizedException("Admin access required");
        }
    }

    public void validateStudentSession(String sessionKey) {
        SessionInfo sessionInfo = validateSession(sessionKey);
        if (sessionInfo.isAdmin()) {
            throw new UnauthorizedException("Student access required");
        }
    }
}
