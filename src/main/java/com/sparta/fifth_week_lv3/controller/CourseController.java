package com.sparta.fifth_week_lv3.controller;

import com.sparta.fifth_week_lv3.dto.CourseDto;
import com.sparta.fifth_week_lv3.entity.Course;
import com.sparta.fifth_week_lv3.service.CourseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // 강의 등록

    @PostMapping("/course")
    //@PreAuthorize("hasRole('ADMIN')")
    public Course registerCourse(@RequestBody CourseDto courseDto) {
        System.out.println("courseDto.getCourseName() = " + courseDto.getCourseName());
        return courseService.registerCourse(courseDto);
    }

    // 강의 수정
    @PutMapping("/course")
    @Secured("ROLE_MANAGER")
    public ResponseEntity<?> updateCourseInfo(Authentication authentication, @PathVariable Long courseId, @RequestBody Course updatedCourse) {
        return courseService.updateCourseInfo(courseId, updatedCourse);
    }
}
