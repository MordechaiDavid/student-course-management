package dev.mordechai.studentcoursemanager.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FatherAppException extends RuntimeException{
    private HttpStatusCode httpStatusCode;
    private List<String> messages;
}
