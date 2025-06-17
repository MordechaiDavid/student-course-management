package dev.mordechai.studentcoursemanager.entity;

import dev.mordechai.studentcoursemanager.dto.request.CourseRegisterRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "course_registrations")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "course_id", nullable = false)
    private Long courseId;

    public static CourseRegistration fromDto(CourseRegisterRequest dto){
        return CourseRegistration.builder()
                .courseId(dto.getCourseId())
                .studentId(dto.getStudentId())
                .build();
    }
}