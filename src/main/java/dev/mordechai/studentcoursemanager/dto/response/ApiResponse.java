package dev.mordechai.studentcoursemanager.dto.response;

import dev.mordechai.studentcoursemanager.exception.ApiErrorResponse;
import lombok.Data;
import lombok.Getter;

@Data
public class ApiResponse<T> {
    private boolean success;
    private T data;
    private ApiErrorResponse error;

    public ApiResponse(T data) {
        this.success = true;
        this.data = data;
    }

    public ApiResponse(ApiErrorResponse error) {
        this.success = false;
        this.error = error;
    }

}
