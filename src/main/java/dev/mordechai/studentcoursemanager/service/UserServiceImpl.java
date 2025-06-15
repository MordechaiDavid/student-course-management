package dev.mordechai.studentcoursemanager.service;

import dev.mordechai.studentcoursemanager.entity.User;
import dev.mordechai.studentcoursemanager.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User create(User user) {

    }

    @Override
    public User getById(Long id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return List.of();
    }

    @Override
    public List<User> getAllStudents() {
        return List.of();
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public User authenticateAdmin(String email, String password) {
        return null;
    }

    @Override
    public User authenticateStudent(String specialKey) {
        return null;
    }

    @Override
    public String regenerateSpecialKey(Long studentId) {
        return "";
    }
}
