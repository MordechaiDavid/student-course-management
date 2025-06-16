package dev.mordechai.studentcoursemanager.dto.response;

import lombok.Data;
import lombok.Getter;

@Data
public class ApiResponse<T> {
    private boolean success;
    private T data;
    private ApiError error;

    public ApiResponse(T data) {
        this.success = true;
        this.data = data;
    }

    public ApiResponse(ApiError error) {
        this.success = false;
        this.error = error;
    }

}
