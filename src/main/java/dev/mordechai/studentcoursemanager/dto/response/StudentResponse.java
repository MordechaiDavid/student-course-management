package dev.mordechai.studentcoursemanager.dto.response;

import dev.mordechai.studentcoursemanager.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {
    private Long id;
    private String name;
    private String email;
    private String secretKey;

    public static StudentResponse fromEntity(Student entity) {
        return StudentResponse.builder().
                id(entity.getId()).
                name(entity.getName()).
                email(entity.getEmail()).
                secretKey(entity.getSpecialKey()).
                build();
    }

}
