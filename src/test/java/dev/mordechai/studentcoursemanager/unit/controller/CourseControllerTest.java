package dev.mordechai.studentcoursemanager.unit.controller;


import dev.mordechai.studentcoursemanager.controller.CourseController;
import dev.mordechai.studentcoursemanager.dto.request.course.CourseCreateRequest;
import dev.mordechai.studentcoursemanager.dto.request.course.CourseUpdateRequest;
import dev.mordechai.studentcoursemanager.dto.response.CourseResponse;
import dev.mordechai.studentcoursemanager.entity.Course;
import dev.mordechai.studentcoursemanager.exception.entity.EntityAlreadyExistException;
import dev.mordechai.studentcoursemanager.exception.entity.EntityNotFoundException;
import dev.mordechai.studentcoursemanager.response.ApiResponse;
import dev.mordechai.studentcoursemanager.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseControllerTest {

    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseController courseController;

    private Course mockCourse;
    private CourseCreateRequest createRequest;
    private CourseUpdateRequest updateRequest;

    @BeforeEach
    void setUp() {
        mockCourse = Course.builder()
                .id(1L)
                .name("Algorithms")
                .description("Algorithm course")
                .build();

        createRequest = CourseCreateRequest.builder()
                .name("Algorithms")
                .description("Algorithm course")
                .build();

        updateRequest = CourseUpdateRequest.builder()
                .name("Advanced Algorithms")
                .description("Advanced topics")
                .build();
    }

    @Test
    void create_ShouldReturnCreatedCourse() {
        when(courseService.create(any(Course.class))).thenReturn(mockCourse);

        ResponseEntity<ApiResponse<CourseResponse>> response = courseController.create(createRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(mockCourse.getName(), response.getBody().getData().getName());
        assertEquals(mockCourse.getDescription(), response.getBody().getData().getDescription());

        verify(courseService, times(1)).create(any(Course.class));
    }

    @Test
    void create_ShouldThrow_WhenNameExists() {
        when(courseService.create(any(Course.class)))
                .thenThrow(new EntityAlreadyExistException("Course with this name already exist"));

        EntityAlreadyExistException ex = assertThrows(EntityAlreadyExistException.class,
                () -> courseController.create(createRequest));
        assertEquals("Course with this name already exist", ex.getMessages().get(0));

        verify(courseService, times(1)).create(any(Course.class));
    }

    @Test
    void getById_ShouldReturnCourse() {
        when(courseService.getById(1L)).thenReturn(mockCourse);

        ResponseEntity<ApiResponse<CourseResponse>> response = courseController.getById(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(mockCourse.getName(), response.getBody().getData().getName());

        verify(courseService, times(1)).getById(1L);
    }

    @Test
    void getById_ShouldThrow_WhenNotFound() {
        when(courseService.getById(1L))
                .thenThrow(new EntityNotFoundException("Course with this ID not found"));

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> courseController.getById(1L));
        assertEquals("Course with this ID not found", ex.getMessages().get(0));

        verify(courseService, times(1)).getById(1L);
    }

    @Test
    void getAll_ShouldReturnAllCourses() {
        List<Course> courses = Arrays.asList(mockCourse);
        when(courseService.getAll()).thenReturn(courses);

        ResponseEntity<ApiResponse<List<CourseResponse>>> response = courseController.getAll();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getData().size());
        assertEquals(mockCourse.getName(), response.getBody().getData().get(0).getName());

        verify(courseService, times(1)).getAll();
    }

    @Test
    void update_ShouldReturnUpdatedCourse() {
        Course updatedCourse = Course.builder()
                .id(1L)
                .name("Advanced Algorithms")
                .description("Advanced topics")
                .build();

        when(courseService.update(eq(1L), any(Course.class))).thenReturn(updatedCourse);

        ResponseEntity<ApiResponse<CourseResponse>> response =
                courseController.update(1L, updateRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(updatedCourse.getName(), response.getBody().getData().getName());

        verify(courseService, times(1)).update(eq(1L), any(Course.class));
    }

    @Test
    void update_ShouldThrow_WhenNotFound() {
        when(courseService.update(eq(1L), any(Course.class)))
                .thenThrow(new EntityNotFoundException("Course with this ID not found"));

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> courseController.update(1L, updateRequest));
        assertEquals("Course with this ID not found", ex.getMessages().get(0));

        verify(courseService, times(1)).update(eq(1L), any(Course.class));
    }

    @Test
    void delete_ShouldReturnSuccessMessage() {
        doNothing().when(courseService).delete(1L);

        ResponseEntity<ApiResponse<String>> response = courseController.delete(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccess());
        assertEquals("Course deleted successfully", response.getBody().getData());

        verify(courseService, times(1)).delete(1L);
    }

    @Test
    void delete_ShouldThrow_WhenNotFound() {
        doThrow(new EntityNotFoundException("Course with this ID not found")).when(courseService).delete(1L);

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> courseController.delete(1L));
        assertEquals("Course with this ID not found", ex.getMessages().get(0));

        verify(courseService, times(1)).delete(1L);
    }
}
