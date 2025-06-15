package dev.mordechai.studentcoursemanager.service;

import dev.mordechai.studentcoursemanager.entity.User;

import java.util.List;

public interface UserService {
    User create(User user);

    User getById(Long id);

    List<User> getAll();

    List<User> getAllStudents();

    User update(User user);

    void delete(User user);

    void deleteById(Long id);

    User authenticateAdmin(String email, String password);

    User authenticateStudent(String specialKey);

    String regenerateSpecialKey(Long studentId);
}
