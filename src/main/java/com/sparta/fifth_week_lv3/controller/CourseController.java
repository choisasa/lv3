package com.sparta.fifth_week_lv3.controller;

import com.sparta.fifth_week_lv3.SecurityUtils;
import com.sparta.fifth_week_lv3.Service.CourseService;
import com.sparta.fifth_week_lv3.dto.CourseDto;
import com.sparta.fifth_week_lv3.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.*;

@RestController

public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    // 강의 등록
    @PostMapping("/course")
    //@PreAuthorize("hasRole('ADMIN')")
    public Course registerCourse(@RequestBody CourseDto courseDto){
        System.out.println("courseDto.getCourseName() = " + courseDto.getCourseName());
        return courseService.registerCourse(courseDto);
    }

    // 강의 수정
    @PutMapping("/course")
    //@Secured("ROLE_MANAGER")
    public ResponseEntity<?> updateCourseInfo(@RequestBody Course updatedCourse){
        Long courseId = updatedCourse.getId();

        // courseId가 null 확인
        if (courseId == null) {
            // courseId가 null 경우에 대한 처리
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Course ID는 null이 아닙니다.");
        }
        if (courseId == null) {
            // courseId가 null 이라면 오류 처리
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("존재하지 않는 courseId 입니다.");
        }
        return courseService.updateCourseInfo(courseId, updatedCourse);
    }

    // 선택한 강의 조회 기능
    @GetMapping("/course/{id}")
    public ResponseEntity<?> getCourseCheck(@PathVariable Long id, @RequestHeader("Authorization") String token){

        // 값 로깅
        System.out.println("Received ID: " + id);
        System.out.println("Received Token: " + token);
        // jwt 토근해서 사용자 역할을 뽑아 관리자인지 확인
        if(!SecurityUtils.isAdmin(token)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("관리자만 접근이 가능합니다.");
        }

        // 관리자의 강의 정보 조회
        CourseDto courseDto = courseService.getCourseCheck(id);
        if(courseDto == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("강의를 찾을 수 없습니다.");
        }
        return ResponseEntity.ok(courseDto);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<?> handleMissingRequestHeaderException (MissingRequestHeaderException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("필수 헤더가 없습니다.: " + ex.getHeaderName());
    }
}
