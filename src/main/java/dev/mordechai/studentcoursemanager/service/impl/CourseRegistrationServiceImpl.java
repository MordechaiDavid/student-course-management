package dev.mordechai.studentcoursemanager.service.impl;

import dev.mordechai.studentcoursemanager.entity.CourseRegistration;
import dev.mordechai.studentcoursemanager.exception.business.CourseCapacityExceededException;
import dev.mordechai.studentcoursemanager.exception.business.StudentCourseLimitExceededException;
import dev.mordechai.studentcoursemanager.exception.entity.EntityAlreadyExistException;
import dev.mordechai.studentcoursemanager.exception.entity.EntityNotFoundException;
import dev.mordechai.studentcoursemanager.repository.CourseRegistrationRepository;
import dev.mordechai.studentcoursemanager.service.CourseRegistrationService;
import dev.mordechai.studentcoursemanager.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CourseRegistrationServiceImpl implements CourseRegistrationService {
    private final CourseRegistrationRepository repository;
    private final CourseService courseService;

    @Autowired
    public CourseRegistrationServiceImpl(CourseRegistrationRepository repository, CourseService courseService) {
        this.repository = repository;
        this.courseService = courseService;
    }

    //FIXME student can register and delete another student registration!!!

    @Override
    public CourseRegistration create(CourseRegistration courseRegistration) {
        if (!courseService.existById(courseRegistration.getCourseId()))
            throw new EntityNotFoundException("Course with this ID dose not exist");
        Optional<CourseRegistration> optional = repository
                .findByStudentIdAndCourseId(courseRegistration.getStudentId(), courseRegistration.getCourseId());
        if (optional.isPresent())
            throw new EntityAlreadyExistException("Student with this ID already register for This course ID");

        List<CourseRegistration> studentsRegistrations = repository.findByStudentId(courseRegistration.getStudentId());
        if (studentsRegistrations.size() >= 2){
            throw new StudentCourseLimitExceededException();
        }
        List<CourseRegistration> courseRegistrations = repository.findByCourseId(courseRegistration.getCourseId());
        if (courseRegistrations.size() >= 30){
            throw new CourseCapacityExceededException();
        }
        log.info("student with id {} register to course {}",  courseRegistration.getStudentId(), courseRegistration.getCourseId() );
        return repository.save(courseRegistration);
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
