package dev.mordechai.studentcoursemanager.documentation;

import dev.mordechai.studentcoursemanager.dto.request.AdminLoginRequest;
import dev.mordechai.studentcoursemanager.dto.request.StudentLoginRequest;
import dev.mordechai.studentcoursemanager.dto.response.LoginResponse;
import dev.mordechai.studentcoursemanager.dto.response.ErrorResponse;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This class contains OpenAPI schema documentation for DTOs.
 * The actual DTOs are kept clean without Swagger annotations.
 */
public class DtoDocumentation {

    @Schema(description = "Admin login request")
    public interface AdminLoginRequestSchema {
        @Schema(description = "Admin email address", example = "admin@example.com", required = true)
        String getEmail();

        @Schema(description = "Admin password", example = "password123", required = true)
        String getPassword();
    }

    @Schema(description = "Student login request")
    public interface StudentLoginRequestSchema {
        @Schema(description = "Student special key", example = "STU-123456", required = true)
        String getSpecialKey();
    }

    @Schema(description = "Login response containing session information")
    public interface LoginResponseSchema {
        @Schema(description = "Session key for authentication", example = "550e8400-e29b-41d4-a716-446655440000")
        String getSessionKey();

        @Schema(description = "Type of user (ADMIN or STUDENT)", example = "ADMIN")
        String getUserType();

        @Schema(description = "User ID", example = "1")
        Long getUserId();

        @Schema(description = "User's full name", example = "John Doe")
        String getName();

        @Schema(description = "User's email address", example = "john.doe@example.com")
        String getEmail();
    }

    @Schema(description = "Error response containing error details")
    public interface ErrorResponseSchema {
        @Schema(description = "Timestamp when the error occurred", example = "2024-03-15T10:30:00")
        String getTimestamp();

        @Schema(description = "HTTP status code", example = "401")
        int getStatus();

        @Schema(description = "HTTP status message", example = "Unauthorized")
        String getError();

        @Schema(description = "Error code for specific error type", example = "AUTH_002")
        String getErrorCode();

        @Schema(description = "Detailed error message", example = "Invalid email or password")
        String getMessage();

        @Schema(description = "API endpoint path where the error occurred", example = "/api/auth/admin/login")
        String getPath();
    }
} 