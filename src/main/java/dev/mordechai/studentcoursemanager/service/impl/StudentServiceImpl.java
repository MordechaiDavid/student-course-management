package dev.mordechai.studentcoursemanager.service.impl;

import dev.mordechai.studentcoursemanager.entity.Student;
import dev.mordechai.studentcoursemanager.exception.entity.EntityAlreadyExistException;
import dev.mordechai.studentcoursemanager.exception.entity.EntityNotFoundException;
import dev.mordechai.studentcoursemanager.repository.StudentRepository;
import dev.mordechai.studentcoursemanager.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public StudentServiceImpl(StudentRepository repository, BCryptPasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Student create(Student student) {
        if (repository.existsByEmail(student.getEmail())) {
            throw new EntityAlreadyExistException("Student with this email already exist");
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
                .orElseThrow(() -> new EntityNotFoundException("Student with this ID not found"));
    }

    @Override
    public Student getByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Student with this email not found"));
    }

    @Override
    public List<Student> getAll() {
        return repository.findAll();
    }

    @Override
    public Student update(Long id, Student student) {
        Student existStudent = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student with this id not found"));
        if (repository.existsByEmail(student.getEmail()))
            throw new EntityAlreadyExistException("Cannot update: Student with this email already exist");
        existStudent.setEmail(student.getEmail());
        log.info("Updating email for student with id: {}", existStudent.getId());
        return repository.save(existStudent);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Student with this id not found");
        }
        log.info("Deleting student with id: {}", id);
        repository.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    private String generateSpecialKey() {
        return UUID.randomUUID().toString();
    }

    public Optional<Student> getBySpecialKey(String specialKey) {
        return repository.findBySpecialKey(specialKey);
    }
} 