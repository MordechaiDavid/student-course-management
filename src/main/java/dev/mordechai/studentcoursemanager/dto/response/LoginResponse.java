package dev.mordechai.studentcoursemanager.dto.response;

import dev.mordechai.studentcoursemanager.entity.Session;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private String sessionKey;
    private String userType;
    private Long userId;

    public static LoginResponse fromEntity(Session entity){
        return LoginResponse.builder()
                .sessionKey(entity.getSessionKey())
                .userId(entity.getUserId())
                .userType(entity.getUserType().name())
                .build();
    }


} 