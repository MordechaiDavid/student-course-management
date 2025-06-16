package dev.mordechai.studentcoursemanager.service.impl;

import dev.mordechai.studentcoursemanager.dto.request.StudentCreateRequest;
import dev.mordechai.studentcoursemanager.dto.request.StudentUpdateRequest;
import dev.mordechai.studentcoursemanager.entity.Student;
import dev.mordechai.studentcoursemanager.exception.student.StudentAlreadyExistsException;
import dev.mordechai.studentcoursemanager.exception.student.StudentNotFoundException;
import dev.mordechai.studentcoursemanager.repository.StudentRepository;
import dev.mordechai.studentcoursemanager.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, BCryptPasswordEncoder passwordEncoder) {
        this.repository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Student create(Student student) {
        if (repository.existsByEmail(student.getEmail())) {
            throw new StudentAlreadyExistsException();
        }
        String specialKey = generateSpecialKey();
        student.setSpecialKey(specialKey);
        //TODO encrypt the special key to db
        log.info("Creating student with email: {}", student.getEmail());
        return repository.save(student);
    }

    @Override
    public Student getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    @Override
    public Student update(Long id, Student student) {
        Student existStudent = repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        existStudent.setEmail(student.getEmail());
        log.info("Updating email for student with id: {}", existStudent.getId());
        return repository.save(existStudent);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new StudentNotFoundException(id);
        }
        log.info("Deleting student with id: {}", id);
        repository.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    private String generateSpecialKey() {
        return "STU-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
} 