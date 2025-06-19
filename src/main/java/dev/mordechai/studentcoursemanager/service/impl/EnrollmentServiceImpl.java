package dev.mordechai.studentcoursemanager.service.impl;

import dev.mordechai.studentcoursemanager.entity.Enrollment;
import dev.mordechai.studentcoursemanager.exception.business.CourseCapacityExceededException;
import dev.mordechai.studentcoursemanager.exception.business.StudentCourseLimitExceededException;
import dev.mordechai.studentcoursemanager.exception.entity.EntityAlreadyExistException;
import dev.mordechai.studentcoursemanager.exception.entity.EntityNotFoundException;
import dev.mordechai.studentcoursemanager.repository.EnrollmentRepository;
import dev.mordechai.studentcoursemanager.service.EnrollmentService;
import dev.mordechai.studentcoursemanager.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepository repository;
    private final CourseService courseService;

    @Autowired
    public EnrollmentServiceImpl(EnrollmentRepository repository, CourseService courseService) {
        this.repository = repository;
        this.courseService = courseService;
    }

    //FIXME student can register and delete another student registration!!!

    @Override
    public Enrollment create(Enrollment enrollment) {
        if (!courseService.existById(enrollment.getCourseId()))
            throw new EntityNotFoundException("Course with this ID dose not exist");
        Optional<Enrollment> optional = repository
                .findByStudentIdAndCourseId(enrollment.getStudentId(), enrollment.getCourseId());
        if (optional.isPresent())
            throw new EntityAlreadyExistException("Student with this ID already register for This course ID");

        List<Enrollment> studentsRegistrations = repository.findByStudentId(enrollment.getStudentId());
        if (studentsRegistrations.size() >= 2){
            throw new StudentCourseLimitExceededException();
        }
        List<Enrollment> enrollments = repository.findByCourseId(enrollment.getCourseId());
        if (enrollments.size() >= 30){
            throw new CourseCapacityExceededException();
        }
        log.info("student with id {} register to course {}",  enrollment.getStudentId(), enrollment.getCourseId() );
        return repository.save(enrollment);
    }

    @Override
    public List<Enrollment> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long studentId, Long courseId) {
        Optional<Enrollment> optional = repository.findByStudentIdAndCourseId(studentId, courseId);
        if (optional.isEmpty()) throw new EntityAlreadyExistException("Student with this ID already register for This course ID");
        log.info("delete registration with student id {} from course {}",  studentId, courseId);
        repository.delete(optional.get());

    }
}
