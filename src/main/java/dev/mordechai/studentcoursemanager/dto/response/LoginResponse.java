package dev.mordechai.studentcoursemanager.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private String sessionKey;
    private String userType;
    private Long userId;
    private String name;
    private String email;
} 