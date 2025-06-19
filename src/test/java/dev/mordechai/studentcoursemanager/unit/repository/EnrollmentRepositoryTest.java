package dev.mordechai.studentcoursemanager.unit.repository;


import dev.mordechai.studentcoursemanager.entity.Enrollment;
import dev.mordechai.studentcoursemanager.repository.EnrollmentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EnrollmentRepositoryTest {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Test
    @DisplayName("should save and find enrollment by id")
    void testFindById() {
        Enrollment enrollment = Enrollment.builder()
                .studentId(10L)
                .courseId(20L)
                .build();
        enrollment = enrollmentRepository.save(enrollment);

        Optional<Enrollment> found = enrollmentRepository.findById(enrollment.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getStudentId()).isEqualTo(10L);
        assertThat(found.get().getCourseId()).isEqualTo(20L);
    }

    @Test
    @DisplayName("should find by studentId and courseId")
    void testFindByStudentIdAndCourseId() {
        Enrollment enrollment = Enrollment.builder()
                .studentId(11L)
                .courseId(21L)
                .build();
        enrollmentRepository.save(enrollment);

        Optional<Enrollment> found = enrollmentRepository.findByStudentIdAndCourseId(11L, 21L);

        assertThat(found).isPresent();
        assertThat(found.get().getStudentId()).isEqualTo(11L);
        assertThat(found.get().getCourseId()).isEqualTo(21L);
    }

    @Test
    @DisplayName("should return empty when not found by studentId and courseId")
    void testFindByStudentIdAndCourseIdNotFound() {
        Optional<Enrollment> found = enrollmentRepository.findByStudentIdAndCourseId(999L, 999L);
        assertThat(found).isNotPresent();
    }

    @Test
    @DisplayName("should find all enrollments by studentId")
    void testFindByStudentId() {
        Enrollment e1 = Enrollment.builder().studentId(12L).courseId(22L).build();
        Enrollment e2 = Enrollment.builder().studentId(12L).courseId(23L).build();
        enrollmentRepository.save(e1);
        enrollmentRepository.save(e2);

        List<Enrollment> found = enrollmentRepository.findByStudentId(12L);

        assertThat(found).hasSize(2);
        assertThat(found).extracting("courseId").contains(22L, 23L);
    }

    @Test
    @DisplayName("should find all enrollments by courseId")
    void testFindByCourseId() {
        Enrollment e1 = Enrollment.builder().studentId(13L).courseId(24L).build();
        Enrollment e2 = Enrollment.builder().studentId(14L).courseId(24L).build();
        enrollmentRepository.save(e1);
        enrollmentRepository.save(e2);

        List<Enrollment> found = enrollmentRepository.findByCourseId(24L);

        assertThat(found).hasSize(2);
        assertThat(found).extracting("studentId").contains(13L, 14L);
    }
}