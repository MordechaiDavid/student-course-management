package dev.mordechai.studentcoursemanager.unit.service;


import dev.mordechai.studentcoursemanager.entity.Enrollment;
import dev.mordechai.studentcoursemanager.exception.business.CourseCapacityExceededException;
import dev.mordechai.studentcoursemanager.exception.business.StudentCourseLimitExceededException;
import dev.mordechai.studentcoursemanager.exception.entity.EntityAlreadyExistException;
import dev.mordechai.studentcoursemanager.exception.entity.EntityNotFoundException;
import dev.mordechai.studentcoursemanager.repository.EnrollmentRepository;
import dev.mordechai.studentcoursemanager.service.CourseService;
import dev.mordechai.studentcoursemanager.service.impl.EnrollmentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Slf4j
class EnrollmentServiceImplTest {

    @Mock
    private EnrollmentRepository repository;

    @Mock
    private CourseService courseService;

    @InjectMocks
    private EnrollmentServiceImpl enrollmentService;

    private Enrollment mockEnrollment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockEnrollment = Enrollment.builder()
                .id(1L)
                .studentId(10L)
                .courseId(20L)
                .build();
    }

    @Test
    void create_ShouldCreateEnrollment_WhenValid() {
        when(courseService.existById(mockEnrollment.getCourseId())).thenReturn(true);
        when(repository.findByStudentIdAndCourseId(mockEnrollment.getStudentId(), mockEnrollment.getCourseId()))
                .thenReturn(Optional.empty());
        when(repository.findByStudentId(mockEnrollment.getStudentId())).thenReturn(Collections.emptyList());
        when(repository.findByCourseId(mockEnrollment.getCourseId())).thenReturn(Collections.emptyList());
        when(repository.save(any(Enrollment.class))).thenAnswer(i -> i.getArgument(0));

        Enrollment result = enrollmentService.create(mockEnrollment);

        assertNotNull(result);
        assertEquals(mockEnrollment.getStudentId(), result.getStudentId());
        assertEquals(mockEnrollment.getCourseId(), result.getCourseId());
        verify(repository).save(any(Enrollment.class));
        log.info("[SUCCESS] Created enrollment: studentId={}, courseId={}", result.getStudentId(), result.getCourseId());
    }

    @Test
    void create_ShouldThrow_WhenCourseNotExist() {
        when(courseService.existById(mockEnrollment.getCourseId())).thenReturn(false);

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> enrollmentService.create(mockEnrollment));
        log.error("[ERROR] Tried to enroll in a non-existent course: {}", mockEnrollment.getCourseId());
        assertEquals("Course with this ID dose not exist", ex.getMessages().get(0));
    }

    @Test
    void create_ShouldThrow_WhenAlreadyRegistered() {
        when(courseService.existById(mockEnrollment.getCourseId())).thenReturn(true);
        when(repository.findByStudentIdAndCourseId(mockEnrollment.getStudentId(), mockEnrollment.getCourseId()))
                .thenReturn(Optional.of(mockEnrollment));

        EntityAlreadyExistException ex = assertThrows(EntityAlreadyExistException.class,
                () -> enrollmentService.create(mockEnrollment));
        log.error("[ERROR] Student already registered for course: studentId={}, courseId={}", mockEnrollment.getStudentId(), mockEnrollment.getCourseId());
        assertEquals("Student with this ID already register for This course ID", ex.getMessages().get(0));
    }

    @Test
    void create_ShouldThrow_WhenStudentHasMaxCourses() {
        when(courseService.existById(mockEnrollment.getCourseId())).thenReturn(true);
        when(repository.findByStudentIdAndCourseId(mockEnrollment.getStudentId(), mockEnrollment.getCourseId()))
                .thenReturn(Optional.empty());

        List<Enrollment> registrations = Arrays.asList(mockEnrollment, mockEnrollment);
        when(repository.findByStudentId(mockEnrollment.getStudentId())).thenReturn(registrations);

        StudentCourseLimitExceededException ex = assertThrows(StudentCourseLimitExceededException.class,
                () -> enrollmentService.create(mockEnrollment));
        log.error("[ERROR] Student {} has exceeded course limit", mockEnrollment.getStudentId());
    }

    @Test
    void create_ShouldThrow_WhenCourseFull() {
        when(courseService.existById(mockEnrollment.getCourseId())).thenReturn(true);
        when(repository.findByStudentIdAndCourseId(mockEnrollment.getStudentId(), mockEnrollment.getCourseId()))
                .thenReturn(Optional.empty());
        when(repository.findByStudentId(mockEnrollment.getStudentId())).thenReturn(Collections.emptyList());

        // Simulate 30 enrollments for course
        List<Enrollment> enrollments = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            enrollments.add(Enrollment.builder().studentId((long) i).courseId(mockEnrollment.getCourseId()).build());
        }
        when(repository.findByCourseId(mockEnrollment.getCourseId())).thenReturn(enrollments);

        CourseCapacityExceededException ex = assertThrows(CourseCapacityExceededException.class,
                () -> enrollmentService.create(mockEnrollment));
        log.error("[ERROR] Course {} is at capacity", mockEnrollment.getCourseId());
    }

    @Test
    void getAll_ShouldReturnListOfEnrollments() {
        List<Enrollment> enrollments = Arrays.asList(mockEnrollment);
        when(repository.findAll()).thenReturn(enrollments);
        List<Enrollment> result = enrollmentService.getAll();
        assertEquals(1, result.size());
        log.info("[SUCCESS] getAll returned {} enrollment(s)", result.size());
    }

    @Test
    void delete_ShouldDelete_WhenEnrollmentExists() {
        when(repository.findByStudentIdAndCourseId(10L, 20L)).thenReturn(Optional.of(mockEnrollment));
        doNothing().when(repository).delete(mockEnrollment);

        assertDoesNotThrow(() -> enrollmentService.delete(10L, 20L));
        verify(repository).delete(mockEnrollment);
        log.info("[SUCCESS] Deleted enrollment: studentId={}, courseId={}", 10L, 20L);
    }

    @Test
    void delete_ShouldThrow_WhenEnrollmentNotExists() {
        when(repository.findByStudentIdAndCourseId(10L, 20L)).thenReturn(Optional.empty());

        EntityAlreadyExistException ex = assertThrows(EntityAlreadyExistException.class,
                () -> enrollmentService.delete(10L, 20L));
        log.error("[ERROR] Tried to delete non-existent enrollment: studentId={}, courseId={}", 10L, 20L);
        assertEquals("Student with this ID already register for This course ID", ex.getMessages().get(0));
    }
}