package dev.mordechai.studentcoursemanager.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ApiError {
    private String code;
    private String message;

}
