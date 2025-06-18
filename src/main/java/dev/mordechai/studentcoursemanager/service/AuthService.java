package dev.mordechai.studentcoursemanager.service;

import dev.mordechai.studentcoursemanager.entity.Session;

public interface AuthService {
    Session authenticateAdminAndGenerateSessionKey(String email, String password);
    Session authenticateStudentAndGenerateSessionKey(String specialKey);
    void logout(String sessionKey);
}