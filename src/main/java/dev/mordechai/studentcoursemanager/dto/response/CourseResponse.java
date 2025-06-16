package dev.mordechai.studentcoursemanager.dto.response;

import dev.mordechai.studentcoursemanager.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponse {
    private Long id;
    private String name;
    private String description;

    public static CourseResponse fromEntity(Course entity) {
        return CourseResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }
}
