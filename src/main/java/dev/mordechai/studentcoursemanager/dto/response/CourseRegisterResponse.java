package dev.mordechai.studentcoursemanager.dto.response;

import dev.mordechai.studentcoursemanager.entity.CourseRegistration;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseRegisterResponse {
    private Long id;
    private Long studentId;
    private Long courseId;

    public static CourseRegisterResponse fromEntity(CourseRegistration entity) {
        return CourseRegisterResponse.builder()
                .courseId(entity.getCourseId())
                .studentId(entity.getStudentId())
                .id(entity.getId())
                .build();
    }
}
