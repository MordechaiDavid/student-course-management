package dev.mordechai.studentcoursemanager.exception;

import dev.mordechai.studentcoursemanager.exception.auth.InvalidCredentialsException;
import dev.mordechai.studentcoursemanager.exception.session.EmptySessionException;
import dev.mordechai.studentcoursemanager.exception.session.InvalidSessionKeyException;
import dev.mordechai.studentcoursemanager.exception.session.SessionKeyExpiredException;
import dev.mordechai.studentcoursemanager.exception.session.UnappropriatedSessionKeyException;
import dev.mordechai.studentcoursemanager.response.ApiResponse;
import dev.mordechai.studentcoursemanager.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * Handles validation errors for @Valid annotated request bodies.
     *
     * @param ex      the exception thrown when validation fails
     * @param request the HTTP request
     * @return a standardized error response with details about the validation failure
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleValidationException(
            MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> errorMessages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        ErrorResponse response = ErrorResponse.builder()
                .httpStatusCode(ex.getStatusCode())
                .messages(errorMessages)
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
                .build();
        log.error("request filed: "+ errorMessages);
        return ResponseEntity.badRequest().body(new ApiResponse<>(response));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleHttpMessageNotReadableException(
            HttpServletRequest request, HttpMessageNotReadableException ex) {

        ErrorResponse response = ErrorResponse.builder()
                .httpStatusCode(HttpStatus.BAD_REQUEST)
                .messages(List.of("empty request or unaccountable format"))
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
                .build();
        log.error("request failed: " + response.getMessages());
        return ResponseEntity.badRequest().body(new ApiResponse<>(response));
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleInvalidCredentialException(
            HttpServletRequest request, InvalidCredentialsException ex){
        ErrorResponse response = ErrorResponse.builder()
                .httpStatusCode(ex.getHttpStatusCode())
                .messages(ex.getMessages())
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
        log.error("invalid credential "+ response.getMessages());
        return ResponseEntity.badRequest().body(new ApiResponse<>(response));
    }



    /**
     * Handles all uncaught exceptions.
     *
     * @param ex      the exception thrown
     * @param request the HTTP request
     * @return a standardized error response for unexpected errors
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleGeneralException(
            HttpServletRequest request, Exception ex) {

        ErrorResponse response = ErrorResponse.builder()
                .httpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .messages(List.of("Some Error in server"))
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
                .build();
        log.error(""+response.getMessages());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(response));
    }


}
