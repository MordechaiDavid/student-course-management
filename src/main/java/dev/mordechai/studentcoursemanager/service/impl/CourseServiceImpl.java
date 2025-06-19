package dev.mordechai.studentcoursemanager.service.impl;

import dev.mordechai.studentcoursemanager.entity.Course;
import dev.mordechai.studentcoursemanager.exception.entity.EntityAlreadyExistException;
import dev.mordechai.studentcoursemanager.exception.entity.EntityNotFoundException;
import dev.mordechai.studentcoursemanager.repository.CourseRepository;
import dev.mordechai.studentcoursemanager.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repository;

    @Autowired
    public CourseServiceImpl(CourseRepository repository) {
        this.repository = repository;
    }


    @Override
    public Course create(Course course) {
        if (repository.existsByName(course.getName())) {
            throw new EntityAlreadyExistException("Course with this name already exist");
        }
        log.info("Creating course with name {}", course.getName());
        return repository.save(course);
    }

    @Override
    public Course getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course with this ID not found"));
    }

    @Override
    public List<Course> getAll() {
        return repository.findAll();
    }

    @Override
    public Course update(Long id, Course course) {
        Course existCourse = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course with this ID not found"));
        existCourse.setDescription(course.getDescription());
        if (repository.existsByName(course.getName()))
            throw new EntityAlreadyExistException("Cannot update: Course with this name already exist");
        existCourse.setName(course.getName());
        log.info("Updating course id: {}", course.getId());
        return repository.save(existCourse);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Course with this ID not found");
        }
        log.info("Deleting course with id: {}", id);
        repository.deleteById(id);
    }

    @Override
    public boolean existById(Long id) {
        return repository.existsById(id);
    }

}
