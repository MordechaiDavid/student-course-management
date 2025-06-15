package dev.mordechai.studentcoursemanager.service.impl;

import dev.mordechai.studentcoursemanager.entity.Admin;
import dev.mordechai.studentcoursemanager.repository.AdminRepository;
import dev.mordechai.studentcoursemanager.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository repository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.repository = adminRepository;
    }


    @Override
    public Optional<Admin> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Admin> getByEmail(String email) {
        return repository.findByEmail(email);
    }
}
