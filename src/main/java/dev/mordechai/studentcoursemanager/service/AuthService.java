package dev.mordechai.studentcoursemanager.service;

import dev.mordechai.studentcoursemanager.entity.Session;

public interface AuthService {
    Session authenticateAdminAndGenerateSessionKey(String email, String password);
    Session authenticateStudent(String specialKey);
    void logout(String sessionKey);
}