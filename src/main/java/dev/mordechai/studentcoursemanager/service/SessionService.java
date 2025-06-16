package dev.mordechai.studentcoursemanager.service;

import dev.mordechai.studentcoursemanager.entity.Session;
import dev.mordechai.studentcoursemanager.entity.UserType;

public interface SessionService {

    Session validateSession(String sessionKey);

    Session validateAdminSession(String sessionKey);

    Session validateStudentSession(String sessionKey);

    boolean isAdmin(UserType userType);
}

