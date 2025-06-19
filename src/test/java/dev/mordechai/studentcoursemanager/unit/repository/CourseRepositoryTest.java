package dev.mordechai.studentcoursemanager.unit.repository;


import dev.mordechai.studentcoursemanager.entity.Course;
import dev.mordechai.studentcoursemanager.repository.CourseRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    @DisplayName("should save and find course by id")
    void testFindById() {
        Course course = Course.builder()
                .name("Physics")
                .description("Physics course")
                .build();
        course = courseRepository.save(course);

        Optional<Course> found = courseRepository.findById(course.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Physics");
    }

    @Test
    @DisplayName("should return true if name exists")
    void testExistsByName() {
        Course course = Course.builder()
                .name("Chemistry")
                .description("Chemistry course")
                .build();
        courseRepository.save(course);

        boolean exists = courseRepository.existsByName("Chemistry");

        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("should return false if name does not exist")
    void testExistsByNameFalse() {
        boolean exists = courseRepository.existsByName("NonExistingCourseName");
        assertThat(exists).isFalse();
    }

    @Test
    @DisplayName("should return true if id exists")
    void testExistsById() {
        Course course = Course.builder()
                .name("Biology")
                .description("Biology course")
                .build();
        course = courseRepository.save(course);

        boolean exists = courseRepository.existsById(course.getId());

        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("should return false if id does not exist")
    void testExistsByIdFalse() {
        boolean exists = courseRepository.existsById(-123L); // unlikely to exist
        assertThat(exists).isFalse();
    }
}
