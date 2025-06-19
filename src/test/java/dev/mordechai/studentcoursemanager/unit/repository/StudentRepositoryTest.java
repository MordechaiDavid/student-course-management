package dev.mordechai.studentcoursemanager.unit.repository;


import dev.mordechai.studentcoursemanager.entity.Student;
import dev.mordechai.studentcoursemanager.repository.StudentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    @DisplayName("should save and find student by secretKey")
    void testFindBySecretKey() {
        String uuid = UUID.randomUUID().toString();
        Student student = Student.builder()
                .name("Alice")
                .email("alice@example.com")
                .secretKey(uuid)
                .build();
        student = studentRepository.save(student);

        Optional<Student> found = studentRepository.findBySecretKey(uuid);

        assertThat(found).isPresent();
        assertThat(found.get().getEmail()).isEqualTo("alice@example.com");
    }

    @Test
    @DisplayName("should return true if secretKey exists")
    void testExistsBySecretKey() {
        String uuid = UUID.randomUUID().toString();
        Student student = Student.builder()
                .name("Bob")
                .email("bob@example.com")
                .secretKey(uuid)
                .build();
        studentRepository.save(student);

        boolean exists = studentRepository.existsBySecretKey(uuid);

        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("should return false if secretKey does not exist")
    void testExistsBySecretKeyFalse() {
        boolean exists = studentRepository.existsBySecretKey("not-existing-uuid");
        assertThat(exists).isFalse();
    }

    @Test
    @DisplayName("should return true if email exists")
    void testExistsByEmail() {
        Student student = Student.builder()
                .name("Charlie")
                .email("charlie@example.com")
                .secretKey(UUID.randomUUID().toString())
                .build();
        studentRepository.save(student);

        boolean exists = studentRepository.existsByEmail("charlie@example.com");

        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("should return false if email does not exist")
    void testExistsByEmailFalse() {
        boolean exists = studentRepository.existsByEmail("notfound@example.com");
        assertThat(exists).isFalse();
    }

    @Test
    @DisplayName("should find by email")
    void testFindByEmail() {
        Student student = Student.builder()
                .name("Daisy")
                .email("daisy@example.com")
                .secretKey(UUID.randomUUID().toString())
                .build();
        studentRepository.save(student);

        Optional<Student> found = studentRepository.findByEmail("daisy@example.com");

        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Daisy");
    }

    @Test
    @DisplayName("should return empty when finding by non-existing email")
    void testFindByEmailNotFound() {
        Optional<Student> found = studentRepository.findByEmail("missing@example.com");

        assertThat(found).isNotPresent();
    }
}
