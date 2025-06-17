package dev.mordechai.studentcoursemanager.controller;

import dev.mordechai.studentcoursemanager.dto.request.CourseRegisterRequest;
import dev.mordechai.studentcoursemanager.dto.request.CourseUnregisterRequest;
import dev.mordechai.studentcoursemanager.dto.response.CourseRegisterResponse;
import dev.mordechai.studentcoursemanager.entity.CourseRegistration;
import dev.mordechai.studentcoursemanager.service.impl.CourseRegistrationServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses/registration")
public class CourseRegistrationController {

    CourseRegistrationServiceImpl service;

    public CourseRegistrationController(CourseRegistrationServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public CourseRegisterResponse register(@RequestBody CourseRegisterRequest request){
        CourseRegistration courseRegistration = service.create(CourseRegistration.fromDto(request));
        return CourseRegisterResponse.fromEntity(courseRegistration);
    }

    @DeleteMapping
    public String cancelRegistration(@RequestBody CourseUnregisterRequest request){
        return "unregister successfully";
    }





}
