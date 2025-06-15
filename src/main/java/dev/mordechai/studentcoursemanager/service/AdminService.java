package dev.mordechai.studentcoursemanager.service;

import dev.mordechai.studentcoursemanager.entity.Admin;

import java.util.Optional;

public interface AdminService {
    Optional<Admin> getById(Long id);

    Optional<Admin> getByEmail(String email);
}
