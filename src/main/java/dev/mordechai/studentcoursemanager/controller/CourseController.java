package dev.mordechai.studentcoursemanager.controller;

import dev.mordechai.studentcoursemanager.service.SessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private SessionService sessionService;

    public CourseController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CourseCreateRequest request,
                                    @RequestHeader("Session-Key") String sessionKey) {
        sessionService.validateAdminSession(sessionKey);
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id,
                                 @RequestHeader("Session-Key") String sessionKey) {
        sessionService.validateAdminSession(sessionKey);
        return null;
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody CoueseUpdateRequest request,
                                    @RequestHeader("Session-Key") String sessionKey) {
        sessionService.validateAdminSession(sessionKey);
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id,
                                    @RequestHeader("Session-Key") String sessionKey) {
        sessionService.validateAdminSession(sessionKey);
        //delete
        return null;
    }
}
