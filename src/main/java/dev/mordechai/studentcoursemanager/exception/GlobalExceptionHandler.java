package dev.mordechai.studentcoursemanager.exception;

import dev.mordechai.studentcoursemanager.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Global exception handler for the Student Course Manager API.
 * Handles validation errors and unexpected exceptions in a unified, maintainable way.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Handles validation errors for @Valid annotated request bodies.
     *
     * @param ex      the exception thrown when validation fails
     * @param request the HTTP request
     * @return a standardized error response with details about the validation failure
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<ApiErrorResponse>> handleValidationException(
            MethodArgumentNotValidException ex, HttpServletRequest request) {

        List<ApiErrorResponse.FieldError> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> ApiErrorResponse.FieldError.builder()
                        .field(error.getField())
                        .message(error.getDefaultMessage())
                        .build())
                .collect(Collectors.toList());

        ApiErrorResponse response = ApiErrorResponse.builder()
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .message("argument validation failed")
                .errors(fieldErrors)
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.badRequest().body(new ApiResponse<>(response));
    }

    /**
     * Handles all uncaught exceptions.
     *
     * @param ex      the exception thrown
     * @param request the HTTP request
     * @return a standardized error response for unexpected errors
     */
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApiResponse<?>> handleGeneralException(Exception ex, HttpServletRequest request) {
//        ex.printStackTrace(); // Consider using a logger in production
//
//        ApiErrorResponse errorResponse = buildErrorResponse(
//                HttpStatus.INTERNAL_SERVER_ERROR,
//                "INTERNAL_ERROR",
//                "Something went wrong",
//                request.getRequestURI(),
//                null
//        );
//
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(errorResponse));
//    }


}
