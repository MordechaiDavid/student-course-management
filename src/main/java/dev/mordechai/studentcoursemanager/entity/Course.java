package dev.mordechai.studentcoursemanager.entity;

import dev.mordechai.studentcoursemanager.dto.request.CourseCreateRequest;
import dev.mordechai.studentcoursemanager.dto.request.CourseUpdateRequest;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 255)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    public static Course fromDto(CourseCreateRequest dto) {
        return Course.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }

    public static Course fromDto(CourseUpdateRequest dto) {
        return Course.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }

}