package dev.mordechai.studentcoursemanager.entity;

import dev.mordechai.studentcoursemanager.dto.request.enrollment.EnrollmentRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "enrollments")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "course_id", nullable = false)
    private Long courseId;

    public static Enrollment fromDto(EnrollmentRequest dto){
        return Enrollment.builder()
                .courseId(dto.getCourseId())
                .studentId(dto.getStudentId())
                .build();
    }
}