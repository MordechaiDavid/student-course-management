package dev.mordechai.studentcoursemanager.unit.controller;

import dev.mordechai.studentcoursemanager.controller.StudentController;
import dev.mordechai.studentcoursemanager.dto.request.course.StudentCreateRequest;
import dev.mordechai.studentcoursemanager.dto.request.student.StudentUpdateRequest;
import dev.mordechai.studentcoursemanager.response.ApiResponse;
import dev.mordechai.studentcoursemanager.dto.response.StudentResponse;
import dev.mordechai.studentcoursemanager.entity.Student;
import dev.mordechai.studentcoursemanager.service.StudentService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    private Student mockStudent;
    private StudentCreateRequest createRequest;
    private StudentUpdateRequest updateRequest;
    private final String MOCK_UUID = "123e4567-e89b-12d3-a456-426614174000";

    @BeforeEach
    void setUp() {
        mockStudent = Student.builder()
                .id(1L)
                .name("John Doe")
                .email("john@example.com")
                .secretKey(MOCK_UUID)
                .build();

        createRequest = new StudentCreateRequest();
        createRequest.setName("John Doe");
        createRequest.setEmail("john@example.com");

        updateRequest = new StudentUpdateRequest();
        updateRequest.setEmail("john.updated@example.com");
    }

    @Test
    void create_ShouldReturnCreatedStudent() {
        when(studentService.create(any(Student.class))).thenReturn(mockStudent);

        ResponseEntity<ApiResponse<StudentResponse>> response = studentController.create(createRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(mockStudent.getEmail(), response.getBody().getData().getEmail());
        assertEquals(mockStudent.getName(), response.getBody().getData().getName());
        assertEquals(mockStudent.getSecretKey(), response.getBody().getData().getSecretKey());

        verify(studentService, times(1)).create(any(Student.class));
    }

    @Test
    void getAll_ShouldReturnAllStudents() {
        List<Student> students = Arrays.asList(mockStudent);
        when(studentService.getAll()).thenReturn(students);

        ResponseEntity<ApiResponse<List<StudentResponse>>> response = studentController.getAll();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getData().size());
        assertEquals(mockStudent.getEmail(), response.getBody().getData().get(0).getEmail());

        verify(studentService, times(1)).getAll();
    }

    @Test
    void getById_ShouldReturnStudent() {

        when(studentService.getById(1L)).thenReturn(mockStudent);

        ResponseEntity<ApiResponse<StudentResponse>> response = studentController.getById(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(mockStudent.getEmail(), response.getBody().getData().getEmail());

        verify(studentService, times(1)).getById(1L);
    }

    @Test
    void getByEmail_ShouldReturnStudent() {

        when(studentService.getByEmail("john@example.com")).thenReturn(mockStudent);

        ResponseEntity<ApiResponse<StudentResponse>> response =
                studentController.getByEmail("john@example.com");

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(mockStudent.getEmail(), response.getBody().getData().getEmail());

        verify(studentService, times(1)).getByEmail("john@example.com");
    }

    @Test
    void update_ShouldReturnUpdatedStudent() {
        Student updatedStudent = Student.builder()
                .id(1L)
                .name("John Doe")
                .email("john.updated@example.com")
                .secretKey(MOCK_UUID)
                .build();

        when(studentService.update(eq(1L), any(Student.class))).thenReturn(updatedStudent);

        ResponseEntity<ApiResponse<StudentResponse>> response =
                studentController.update(1L, updateRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(updatedStudent.getEmail(), response.getBody().getData().getEmail());

        verify(studentService, times(1)).update(eq(1L), any(Student.class));
    }

    @Test
    void delete_ShouldReturnSuccessMessage() {
        doNothing().when(studentService).delete(1L);

        ResponseEntity<ApiResponse<String>> response = studentController.delete(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccess());
        assertEquals("Student deleted successfully", response.getBody().getData());

        verify(studentService, times(1)).delete(1L);
    }
}
