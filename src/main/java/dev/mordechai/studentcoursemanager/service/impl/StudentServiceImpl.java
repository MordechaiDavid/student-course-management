package dev.mordechai.studentcoursemanager.service.impl;

import dev.mordechai.studentcoursemanager.dto.request.StudentCreateRequest;
import dev.mordechai.studentcoursemanager.dto.request.StudentUpdateRequest;
import dev.mordechai.studentcoursemanager.entity.Student;
import dev.mordechai.studentcoursemanager.exception.student.StudentNotFoundException;
import dev.mordechai.studentcoursemanager.repository.StudentRepository;
import dev.mordechai.studentcoursemanager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student create(StudentCreateRequest request) {
        Student student = new Student();
        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setSpecialKey(generateSpecialKey());
        
        return studentRepository.save(student);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student update(Long id, StudentUpdateRequest request) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        student.setName(request.getName());
        student.setEmail(request.getEmail());

        return studentRepository.save(student);
    }

    @Override
    public void delete(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return studentRepository.existsByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return studentRepository.existsById(id);
    }

    private String generateSpecialKey() {
        return "STU-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
} 