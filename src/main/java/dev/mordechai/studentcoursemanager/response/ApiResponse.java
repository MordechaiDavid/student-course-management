package dev.mordechai.studentcoursemanager.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private T data;
    public ErrorResponse error;

    public ApiResponse(T data) {
        this.success = true;
        this.data = data;
    }

    public ApiResponse(ErrorResponse error) {
        this.success = false;
        this.error = error;
    }

}
