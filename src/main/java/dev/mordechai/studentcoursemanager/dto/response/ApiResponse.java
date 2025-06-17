package dev.mordechai.studentcoursemanager.dto.response;

import dev.mordechai.studentcoursemanager.exception.ErrorResponse;
import lombok.Data;

@Data
public class ApiResponse<T> {
    private boolean success;
    private T data;
    private ErrorResponse error;

    public ApiResponse(T data) {
        this.success = true;
        this.data = data;
    }

    public ApiResponse(ErrorResponse error) {
        this.success = false;
        this.error = error;
    }

}
