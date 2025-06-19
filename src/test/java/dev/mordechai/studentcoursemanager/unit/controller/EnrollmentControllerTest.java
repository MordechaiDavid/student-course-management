package dev.mordechai.studentcoursemanager.unit.controller;

import dev.mordechai.studentcoursemanager.controller.EnrollmentController;
import dev.mordechai.studentcoursemanager.dto.request.enrollment.EnrollmentRequest;
import dev.mordechai.studentcoursemanager.dto.request.enrollment.UnenrollmentRequest;
import dev.mordechai.studentcoursemanager.dto.response.EnrollmentResponse;
import dev.mordechai.studentcoursemanager.entity.Enrollment;
import dev.mordechai.studentcoursemanager.exception.entity.EntityAlreadyExistException;
import dev.mordechai.studentcoursemanager.exception.entity.EntityNotFoundException;
import dev.mordechai.studentcoursemanager.response.ApiResponse;
import dev.mordechai.studentcoursemanager.service.EnrollmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnrollmentControllerTest {

    @Mock
    private EnrollmentService enrollmentService;

    @InjectMocks
    private EnrollmentController enrollmentController;

    private Enrollment mockEnrollment;
    private EnrollmentRequest enrollmentRequest;
    private UnenrollmentRequest unenrollmentRequest;

    @BeforeEach
    void setUp() {
        mockEnrollment = Enrollment.builder()
                .id(1L)
                .studentId(101L)
                .courseId(202L)
                .build();

        enrollmentRequest = new EnrollmentRequest();
        enrollmentRequest.setStudentId(101L);
        enrollmentRequest.setCourseId(202L);

        unenrollmentRequest = new UnenrollmentRequest();
        unenrollmentRequest.setStudentId(101L);
        unenrollmentRequest.setCourseId(202L);
    }

    @Test
    @DisplayName("should register and return created enrollment")
    void testRegister() {
        when(enrollmentService.create(any(Enrollment.class))).thenReturn(mockEnrollment);

        ResponseEntity<ApiResponse<EnrollmentResponse>> response = enrollmentController.register(enrollmentRequest);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getData().getStudentId()).isEqualTo(101L);
        assertThat(response.getBody().getData().getCourseId()).isEqualTo(202L);

        verify(enrollmentService, times(1)).create(any(Enrollment.class));
    }

    @Test
    @DisplayName("should throw when student already registered for course")
    void testRegister_AlreadyExists() {
        when(enrollmentService.create(any(Enrollment.class)))
                .thenThrow(new EntityAlreadyExistException("Student with this ID already register for This course ID"));

        EntityAlreadyExistException ex = assertThrows(EntityAlreadyExistException.class,
                () -> enrollmentController.register(enrollmentRequest));
        assertThat(ex.getMessages().get(0)).isEqualTo("Student with this ID already register for This course ID");

        verify(enrollmentService, times(1)).create(any(Enrollment.class));
    }

    @Test
    @DisplayName("should throw when course does not exist")
    void testRegister_CourseNotFound() {
        when(enrollmentService.create(any(Enrollment.class)))
                .thenThrow(new EntityNotFoundException("Course with this ID dose not exist"));

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> enrollmentController.register(enrollmentRequest));
        assertThat(ex.getMessages().get(0)).isEqualTo("Course with this ID dose not exist");

        verify(enrollmentService, times(1)).create(any(Enrollment.class));
    }

    @Test
    @DisplayName("should unregister and return success message")
    void testCancelRegistration() {
        doNothing().when(enrollmentService).delete(101L, 202L);

        ResponseEntity<ApiResponse<String>> response = enrollmentController.cancelRegistration(unenrollmentRequest);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().isSuccess()).isTrue();
        assertThat(response.getBody().getData()).isEqualTo("Unregister successfully");

        verify(enrollmentService, times(1)).delete(101L, 202L);
    }

    @Test
    @DisplayName("should throw when unregistering non-existent enrollment")
    void testCancelRegistration_NotRegistered() {
        doThrow(new EntityAlreadyExistException("Student with this ID already register for This course ID"))
                .when(enrollmentService).delete(101L, 202L);

        EntityAlreadyExistException ex = assertThrows(EntityAlreadyExistException.class,
                () -> enrollmentController.cancelRegistration(unenrollmentRequest));
        assertThat(ex.getMessages().get(0)).isEqualTo("Student with this ID already register for This course ID");

        verify(enrollmentService, times(1)).delete(101L, 202L);
    }
}
