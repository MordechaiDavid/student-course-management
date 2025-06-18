package dev.mordechai.studentcoursemanager.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse{
    private HttpStatusCode httpStatusCode;
    private List<String> messages;
    private LocalDateTime timestamp;
    private String path;
}
