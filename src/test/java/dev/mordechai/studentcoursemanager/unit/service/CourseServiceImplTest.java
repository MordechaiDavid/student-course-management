package dev.mordechai.studentcoursemanager.unit.service;


import dev.mordechai.studentcoursemanager.entity.Course;
import dev.mordechai.studentcoursemanager.exception.entity.EntityAlreadyExistException;
import dev.mordechai.studentcoursemanager.exception.entity.EntityNotFoundException;
import dev.mordechai.studentcoursemanager.repository.CourseRepository;
import dev.mordechai.studentcoursemanager.service.impl.CourseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Slf4j
class CourseServiceImplTest {

    @Mock
    private CourseRepository repository;

    @InjectMocks
    private CourseServiceImpl courseService;

    private Course mockCourse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockCourse = Course.builder()
                .id(1L)
                .name("Algorithms")
                .description("Algorithm course")
                .build();
    }

    @Test
    void create_ShouldCreateCourse_WhenNameIsUnique() {
        when(repository.existsByName(mockCourse.getName())).thenReturn(false);
        when(repository.save(any(Course.class))).thenAnswer(i -> i.getArgument(0));

        Course result = courseService.create(mockCourse);

        assertNotNull(result);
        assertEquals(mockCourse.getName(), result.getName());
        verify(repository).save(any(Course.class));
        log.info("[SUCCESS] Created course: name={}", result.getName());
    }

    @Test
    void create_ShouldThrow_WhenNameExists() {
        when(repository.existsByName(mockCourse.getName())).thenReturn(true);

        EntityAlreadyExistException ex = assertThrows(EntityAlreadyExistException.class,
                () -> courseService.create(mockCourse));
        log.error("[ERROR] Tried to create course with existing name: {}", mockCourse.getName());

        assertEquals("Course with this name already exist", ex.getMessages().get(0));
    }

    @Test
    void getById_ShouldReturnCourse_WhenExists() {
        when(repository.findById(1L)).thenReturn(Optional.of(mockCourse));
        Course result = courseService.getById(1L);
        assertEquals(mockCourse.getName(), result.getName());
        log.info("[SUCCESS] Found course by ID={} with name={}", result.getId(), result.getName());
    }

    @Test
    void getById_ShouldThrow_WhenNotExists() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class, () -> courseService.getById(1L));
        log.error("[ERROR] Course not found by ID={}", 1L);
        assertEquals("Course with this ID not found", ex.getMessages().get(0));
    }

    @Test
    void getAll_ShouldReturnListOfCourses() {
        List<Course> courses = Arrays.asList(mockCourse);
        when(repository.findAll()).thenReturn(courses);
        List<Course> result = courseService.getAll();
        assertEquals(1, result.size());
        log.info("[SUCCESS] getAll returned {} course(s)", result.size());
    }

    @Test
    void update_ShouldUpdateCourse_WhenValid() {
        Course update = Course.builder().name("Advanced Algorithms").description("Advanced topics").build();
        when(repository.findById(1L)).thenReturn(Optional.of(mockCourse));
        when(repository.existsByName(update.getName())).thenReturn(false);
        when(repository.save(any(Course.class))).thenAnswer(i -> i.getArgument(0));

        Course result = courseService.update(1L, update);

        assertEquals("Advanced Algorithms", result.getName());
        assertEquals("Advanced topics", result.getDescription());
        log.info("[SUCCESS] Updated course ID={} to new name={}", result.getId(), result.getName());
    }

    @Test
    void update_ShouldThrow_WhenCourseNotFound() {
        Course update = Course.builder().name("Advanced Algorithms").build();
        when(repository.findById(1L)).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> courseService.update(1L, update));
        log.error("[ERROR] Tried to update non-existent course ID={}", 1L);
        assertEquals("Course with this ID not found", ex.getMessages().get(0));
    }

    @Test
    void update_ShouldThrow_WhenNameExists() {
        Course update = Course.builder().name("Taken Name").description("desc").build();
        when(repository.findById(1L)).thenReturn(Optional.of(mockCourse));
        when(repository.existsByName(update.getName())).thenReturn(true);

        EntityAlreadyExistException ex = assertThrows(EntityAlreadyExistException.class,
                () -> courseService.update(1L, update));
        log.error("[ERROR] Tried to update course ID={} to taken name={}", 1L, update.getName());
        assertEquals("Cannot update: Course with this name already exist", ex.getMessages().get(0));
    }

    @Test
    void delete_ShouldDelete_WhenExists() {
        when(repository.existsById(1L)).thenReturn(true);
        doNothing().when(repository).deleteById(1L);

        assertDoesNotThrow(() -> courseService.delete(1L));
        verify(repository).deleteById(1L);
        log.info("[SUCCESS] Deleted course ID={}", 1L);
    }

    @Test
    void delete_ShouldThrow_WhenNotExists() {
        when(repository.existsById(1L)).thenReturn(false);
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class, () -> courseService.delete(1L));
        log.error("[ERROR] Tried to delete non-existent course ID={}", 1L);
        assertEquals("Course with this ID not found", ex.getMessages().get(0));
    }

    @Test
    void existById_ShouldReturnCorrectResult() {
        when(repository.existsById(1L)).thenReturn(true);
        boolean exists = courseService.existById(1L);
        assertTrue(exists);
        log.info("[SUCCESS] existById returned true for id={}", 1L);
    }
}
