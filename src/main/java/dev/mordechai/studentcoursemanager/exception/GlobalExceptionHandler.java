package dev.mordechai.studentcoursemanager.exception;

import dev.mordechai.studentcoursemanager.exception.auth.InvalidCredentialsException;
import dev.mordechai.studentcoursemanager.exception.entity.EntityAlreadyExistException;
import dev.mordechai.studentcoursemanager.exception.entity.EntityNotFoundException;
import dev.mordechai.studentcoursemanager.response.ApiResponse;
import dev.mordechai.studentcoursemanager.response.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

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

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<Object>> handleTypeMismatch(
            MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        ErrorResponse response = ErrorResponse.builder()
                .httpStatusCode(HttpStatus.BAD_REQUEST)
                .messages(List.of("Invalid format for " + ex.getName() + ": must be a valid number"))
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
        log.error("Request failed: " + response.getMessages());
        return ResponseEntity.badRequest().body(new ApiResponse<>(response));
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleInvalidCredentialException(
            HttpServletRequest request, InvalidCredentialsException ex){
        ErrorResponse response = buildResponse(request, ex);
        log.error("invalid credential "+ response.getMessages());
        return ResponseEntity.badRequest().body(new ApiResponse<>(response));
    }

    @ExceptionHandler(EntityAlreadyExistException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleEntityAlreadyExistException(
            HttpServletRequest request, EntityAlreadyExistException ex){
        ErrorResponse response = buildResponse(request, ex);
        log.error("Cannot create entity: "+response.getMessages());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse<>(response));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleConstraintViolationException(
            HttpServletRequest request, ConstraintViolationException ex){
        ErrorResponse response = ErrorResponse.builder()
                .httpStatusCode(HttpStatus.BAD_REQUEST)
                .messages(List.of(ex.getLocalizedMessage()))
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
                .build();
        log.error("error in request filed: " + response.getMessages());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(response));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleEntityNotFoundException(
            HttpServletRequest request, EntityNotFoundException ex){
        ErrorResponse response = buildResponse(request, ex);
        log.error("Entity not found: "+response.getMessages());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(response));
    }





    private ErrorResponse buildResponse(HttpServletRequest request, FatherAppException ex){
        return ErrorResponse.builder()
                .httpStatusCode(ex.getHttpStatusCode())
                .messages(ex.getMessages())
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
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
