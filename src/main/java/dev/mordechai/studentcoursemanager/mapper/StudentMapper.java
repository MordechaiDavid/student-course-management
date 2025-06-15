package dev.mordechai.studentcoursemanager.mapper;

import dev.mordechai.studentcoursemanager.dto.request.StudentCreateRequest;
import dev.mordechai.studentcoursemanager.dto.request.StudentUpdateRequest;
import dev.mordechai.studentcoursemanager.dto.response.StudentResponse;
import dev.mordechai.studentcoursemanager.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper implements BaseMapper<Student, StudentCreateRequest, StudentUpdateRequest, StudentResponse> {

    @Override
    public Student toEntity(StudentCreateRequest request) {
        return Student.builder()
                .name(request.getName())
                .email(request.getEmail())
                .specialKey(generateSpecialKey())
                .build();
    }

    @Override
    public StudentResponse toResponse(Student student) {
        return StudentResponse.builder()
                .id(student.getId())
                .name(student.getName())
                .email(student.getEmail())
                .build();
    }

    @Override
    public void updateEntity(Student student, StudentUpdateRequest request) {
        student.setEmail(request.getEmail());
    }

    private String generateSpecialKey() {
        return "STU-" + java.util.UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
} 