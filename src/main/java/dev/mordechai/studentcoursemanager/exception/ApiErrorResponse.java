package dev.mordechai.studentcoursemanager.exception;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ApiErrorResponse {
    private LocalDateTime timestamp;
    private int httpStatus;
    private String message;
    private String path;
    private List<FieldError> errors;

    @Data
    @Builder
    public static class FieldError {
        private String field;
        private String message;
    }
}
