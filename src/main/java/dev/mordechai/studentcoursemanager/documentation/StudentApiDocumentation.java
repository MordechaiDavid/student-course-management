package dev.mordechai.studentcoursemanager.documentation;

import dev.mordechai.studentcoursemanager.dto.request.StudentCreateRequest;
import dev.mordechai.studentcoursemanager.dto.request.StudentUpdateRequest;
import dev.mordechai.studentcoursemanager.dto.response.ErrorResponse;
import dev.mordechai.studentcoursemanager.entity.Student;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * This class contains OpenAPI documentation for Student Management APIs.
 * The actual implementation is in StudentController.
 */
@Tag(name = "Student Management", description = "APIs for managing students")
public interface StudentApiDocumentation {

    @Operation(
        summary = "Create Student",
        description = "Creates a new student with the provided details"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Student created successfully",
            content = @Content(schema = @Schema(implementation = Student.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid request format",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - Invalid session",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Forbidden - Admin access required",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
        )
    })
    Student createStudent(StudentCreateRequest request);

    @Operation(
        summary = "Get Student",
        description = "Retrieves a student by their ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Student found",
            content = @Content(schema = @Schema(implementation = Student.class))
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - Invalid session",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Forbidden - Admin access required",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Student not found",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
        )
    })
    Student getStudent(Long id);

    @Operation(
        summary = "Update Student",
        description = "Updates an existing student's details"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Student updated successfully",
            content = @Content(schema = @Schema(implementation = Student.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid request format",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - Invalid session",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Forbidden - Admin access required",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Student not found",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
        )
    })
    Student updateStudent(Long id, StudentUpdateRequest request);

    @Operation(
        summary = "Delete Student",
        description = "Deletes a student by their ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Student deleted successfully"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - Invalid session",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Forbidden - Admin access required",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Student not found",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
        )
    })
    void deleteStudent(Long id);
} 