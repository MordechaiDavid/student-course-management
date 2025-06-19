package dev.mordechai.studentcoursemanager.service.impl;

import dev.mordechai.studentcoursemanager.entity.Course;
import dev.mordechai.studentcoursemanager.repository.CourseRepository;
import dev.mordechai.studentcoursemanager.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repository;

    public CourseServiceImpl(CourseRepository repository) {
        this.repository = repository;
    }


    @Override
    public Course create(Course course) {
        if (repository.existsByName(course.getName())) {
            throw new RuntimeException();
        }
        log.info("Creating course with name {}", course.getName());
        return repository.save(course);
    }

    @Override
    public Course getById(Long id) {
        return repository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Course> getAll() {
        return repository.findAll();
    }

    @Override
    public Course update(Long id, Course course) {
        Course existCourse = repository.findById(id)
                .orElseThrow(() -> new RuntimeException());
        existCourse.setDescription(course.getDescription());
        existCourse.setName(course.getName());
        log.info("Updating course id: {}", course.getId());
        return repository.save(existCourse);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException();
        }
        log.info("Deleting course with id: {}", id);
        repository.deleteById(id);
    }

    @Override
    public boolean existById(Long id) {
        return repository.existsById(id);
    }

}
