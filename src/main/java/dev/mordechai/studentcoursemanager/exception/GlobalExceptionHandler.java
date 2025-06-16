package dev.mordechai.studentcoursemanager.exception;

import dev.mordechai.studentcoursemanager.dto.response.ApiError;
import dev.mordechai.studentcoursemanager.dto.response.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationException(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String message = fieldError != null
                ? fieldError.getField() + ": " + fieldError.getDefaultMessage()
                : "Validation failed";

        ApiError apiError = new ApiError("VALIDATION_ERROR", message);
        return ResponseEntity.badRequest().body(new ApiResponse<>(apiError));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<?>> handleConstraintViolationException(ConstraintViolationException ex) {
        String message = ex.getConstraintViolations()
                .stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .findFirst()
                .orElse("Constraint violation");

        ApiError apiError = new ApiError("CONSTRAINT_VIOLATION", message);
        return ResponseEntity.badRequest().body(new ApiResponse<>(apiError));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGeneralException(Exception ex) {
        ex.printStackTrace();
        ApiError apiError = new ApiError("INTERNAL_ERROR", "Something went wrong");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(apiError));
    }
}
