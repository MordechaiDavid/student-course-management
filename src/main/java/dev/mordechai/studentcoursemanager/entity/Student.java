package dev.mordechai.studentcoursemanager.entity;

import dev.mordechai.studentcoursemanager.dto.request.course.StudentCreateRequest;
import dev.mordechai.studentcoursemanager.dto.request.StudentUpdateRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "special_key", nullable = false, unique = true, length = 255)
    private String specialKey;

    public static Student fromDto(StudentCreateRequest dto){
        return Student.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .build();
    }

    public static Student fromDto(StudentUpdateRequest dto){
        return Student.builder()
                .email(dto.getEmail())
                .build();
    }
    
} 