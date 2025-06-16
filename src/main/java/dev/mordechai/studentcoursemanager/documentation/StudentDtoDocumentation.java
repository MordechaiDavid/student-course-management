package dev.mordechai.studentcoursemanager.documentation;

import dev.mordechai.studentcoursemanager.entity.Student;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This class contains OpenAPI schema documentation for Student-related DTOs.
 * The actual DTOs are kept clean without Swagger annotations.
 */
public class StudentDtoDocumentation {

    @Schema(description = "Student entity")
    public interface StudentSchema {
        @Schema(description = "Student ID", example = "1")
        Long getId();

        @Schema(description = "Student's full name", example = "John Doe")
        String getName();

        @Schema(description = "Student's email address", example = "john.doe@example.com")
        String getEmail();

        @Schema(description = "Student's special key for login", example = "STU-12345678")
        String getSpecialKey();
    }

    @Schema(description = "Request for creating a new student")
    public interface StudentCreateRequestSchema {
        @Schema(description = "Student's full name", example = "John Doe", required = true)
        String getName();

        @Schema(description = "Student's email address", example = "john.doe@example.com", required = true)
        String getEmail();
    }

    @Schema(description = "Request for updating an existing student")
    public interface StudentUpdateRequestSchema {
        @Schema(description = "Student's full name", example = "John Doe", required = true)
        String getName();

        @Schema(description = "Student's email address", example = "john.doe@example.com", required = true)
        String getEmail();
    }
} 