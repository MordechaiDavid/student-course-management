package dev.mordechai.studentcoursemanager.dto.response;

import dev.mordechai.studentcoursemanager.entity.Student;
import lombok.Builder;
import lombok.Data;

import java.util.Optional;

@Data
@Builder
public class StudentResponse {
    private Long id;
    private String name;
    private String email;

    public static StudentResponse fromEntity(Student entity) {
        return StudentResponse.builder().id(entity.getId()).name(entity.getName()).email(entity.getEmail()).build();
    }

}
