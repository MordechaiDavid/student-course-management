package dev.mordechai.studentcoursemanager.dto.response;

import dev.mordechai.studentcoursemanager.entity.Enrollment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnrollmentResponse {
    private Long id;
    private Long studentId;
    private Long courseId;

    public static EnrollmentResponse fromEntity(Enrollment entity) {
        return EnrollmentResponse.builder()
                .courseId(entity.getCourseId())
                .studentId(entity.getStudentId())
                .id(entity.getId())
                .build();
    }
}
