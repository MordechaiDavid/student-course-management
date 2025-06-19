package dev.mordechai.studentcoursemanager.unit.controller;


import dev.mordechai.studentcoursemanager.controller.AdminDashboardController;
import dev.mordechai.studentcoursemanager.dto.entity.course.CourseWithStudentsDTO;
import dev.mordechai.studentcoursemanager.dto.entity.student.StudentWithCoursesDTO;
import dev.mordechai.studentcoursemanager.response.ApiResponse;
import dev.mordechai.studentcoursemanager.service.StudentCourseQueryService;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminDashboardControllerTest {

    @Mock
    private StudentCourseQueryService service;

    @InjectMocks
    private AdminDashboardController controller;

    private List<StudentWithCoursesDTO> mockStudentWithCoursesList;
    private List<CourseWithStudentsDTO> mockCourseWithStudentsList;

    @BeforeEach
    void setUp() {
        StudentWithCoursesDTO studentWithCourses = StudentWithCoursesDTO.builder()
                .id(1L)
                .name("John Doe")
                .courses(Arrays.asList())
                .build();
        mockStudentWithCoursesList = Arrays.asList(studentWithCourses);

        CourseWithStudentsDTO courseWithStudents = CourseWithStudentsDTO.builder()
                .id(1L)
                .name("Math")
                .students(Arrays.asList())
                .build();
        mockCourseWithStudentsList = Arrays.asList(courseWithStudents);
    }

    @Test
    void getAllStudentsWithCourses_ShouldReturnAllStudentsWithCourses() {
        when(service.getAllStudentsWithCourses()).thenReturn(mockStudentWithCoursesList);

        ResponseEntity<ApiResponse<List<StudentWithCoursesDTO>>> response = controller.getAllStudentsWithCourses();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getData().size());
        assertEquals("John Doe", response.getBody().getData().get(0).getName());

        verify(service, times(1)).getAllStudentsWithCourses();
    }

    @Test
    void getAllCoursesWithStudents_ShouldReturnAllCoursesWithStudents() {
        when(service.getAllCoursesWithStudents()).thenReturn(mockCourseWithStudentsList);

        ResponseEntity<ApiResponse<List<CourseWithStudentsDTO>>> response = controller.getAllCoursesWithStudents();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getData().size());
        assertEquals("Math", response.getBody().getData().get(0).getName());

        verify(service, times(1)).getAllCoursesWithStudents();
    }
}
