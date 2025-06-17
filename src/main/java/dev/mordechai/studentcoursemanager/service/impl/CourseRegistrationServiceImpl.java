package dev.mordechai.studentcoursemanager.service.impl;

import dev.mordechai.studentcoursemanager.entity.CourseRegistration;
import dev.mordechai.studentcoursemanager.repository.CourseRegistrationRepository;
import dev.mordechai.studentcoursemanager.service.CourseRegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CourseRegistrationServiceImpl implements CourseRegistrationService {
    private final CourseRegistrationRepository repository;

    @Autowired
    public CourseRegistrationServiceImpl(CourseRegistrationRepository repository) {
        this.repository = repository;
    }

    //FIXME student can register and delete another student registration!!!

    @Override
    public CourseRegistration create(CourseRegistration courseRegistration) {
        Optional<CourseRegistration> optional = repository.findByStudentIdAndCourseId(courseRegistration.getStudentId(), courseRegistration.getCourseId());
        if (optional.isPresent()) throw new RuntimeException("exists");

        List<CourseRegistration> studentsRegistrations = repository.findByStudentId(courseRegistration.getStudentId());
        List<CourseRegistration> courseRegistrations = repository.findByCourseId(courseRegistration.getCourseId());
        if (studentsRegistrations.size() >= 2 || courseRegistrations.size() >= 30)
            throw new RuntimeException("business logic exceeded");
        log.info("student with id {} register to course {}",  courseRegistration.getStudentId(), courseRegistration.getCourseId() );
        return  repository.save(courseRegistration);
    }

    @Override
    public List<CourseRegistration> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long studentId, Long courseId) {
        Optional<CourseRegistration> optional = repository.findByStudentIdAndCourseId(studentId, courseId);
        if (optional.isEmpty()) throw new RuntimeException("not exists");
        log.info("delete registration with student id {} from course {}",  studentId, courseId);
        repository.delete(optional.get());

    }
}
