package dev.mordechai.studentcoursemanager.documentation;

import dev.mordechai.studentcoursemanager.dto.request.AdminLoginRequest;
import dev.mordechai.studentcoursemanager.dto.request.StudentLoginRequest;
import dev.mordechai.studentcoursemanager.dto.response.LoginResponse;
import dev.mordechai.studentcoursemanager.dto.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * This class contains OpenAPI documentation for Authentication APIs.
 * The actual implementation is in AuthController.
 */
@Tag(name = "Authentication", description = "Authentication management APIs")
public interface AuthApiDocumentation {

    @Operation(
        summary = "Admin Login",
        description = "Authenticates an admin user and returns a session key"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Login successful",
            content = @Content(schema = @Schema(implementation = LoginResponse.class))
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Invalid credentials",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid request format",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
        )
    })
    LoginResponse adminLogin(AdminLoginRequest request);

    @Operation(
        summary = "Student Login",
        description = "Authenticates a student using their special key and returns a session key"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Login successful",
            content = @Content(schema = @Schema(implementation = LoginResponse.class))
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Invalid special key",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid request format",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
        )
    })
    LoginResponse studentLogin(StudentLoginRequest request);

    @Operation(
        summary = "Logout",
        description = "Invalidates the current session"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Logout successful"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Invalid session key",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
        )
    })
    void logout(String sessionKey);
} 