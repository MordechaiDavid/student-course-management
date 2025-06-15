package dev.mordechai.studentcoursemanager.mapper;

import dev.mordechai.studentcoursemanager.dto.request.CourseCreateRequest;
import dev.mordechai.studentcoursemanager.dto.request.CourseUpdateRequest;
import dev.mordechai.studentcoursemanager.dto.response.CourseResponse;
import dev.mordechai.studentcoursemanager.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper implements BaseMapper<Course, CourseCreateRequest, CourseUpdateRequest, CourseResponse> {

    @Override
    public Course toEntity(CourseCreateRequest request) {
        return Course.builder()
                .name(request.getName())
                .description(request.getDescription())
                .createdAt(java.time.LocalDateTime.now())
                .build();
    }

    @Override
    public CourseResponse toResponse(Course course) {
        return CourseResponse.builder()
                .id(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .createdAt(course.getCreatedAt())
                .build();
    }

    @Override
    public void updateEntity(Course course, CourseUpdateRequest request) {
        course.setName(request.getName());
        course.setDescription(request.getDescription());
    }
} 