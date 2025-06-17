package dev.mordechai.studentcoursemanager.dto.request;

import lombok.Data;

@Data
public class CourseUnregisterRequest {
    private Long studentId;
    private Long courseId;
}
