package com.sparta.fifth_week_lv3.controller;
import com.sparta.fifth_week_lv3.SecurityUtils;
import com.sparta.fifth_week_lv3.Service.CourseService;
import com.sparta.fifth_week_lv3.dto.CourseDto;
import com.sparta.fifth_week_lv3.dto.CourseResponseDto;
import com.sparta.fifth_week_lv3.entity.Course;
import com.sparta.fifth_week_lv3.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "강의 API", description = "강의 등록, 수정, 조회 API")
public class CourseController {

    private final CourseService courseService;
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // 강의 등록
    @PostMapping("/course")
    @Operation(summary = "강의 등록", description = "새로운 강의를 등록합니다.")
    public ResponseEntity<CourseResponseDto> registerCourse(@RequestBody CourseDto courseDto) {
        CourseResponseDto responseDto = courseService.registerCourse(courseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    // 강의 조회
    @GetMapping("/course/{courseName}")
    @Operation(summary = "강의 이름으로 강의 조회", description = "선택한 강의 이름으로 강의 정보를 조회합니다.")
    public ResponseEntity<CourseResponseDto> getCourseByName(
            @PathVariable String courseName
    ) {
        CourseResponseDto course = courseService.getCourseByName(courseName);
        return ResponseEntity.ok(course);
    }

    // 카테고리별 강의 목록 조회
    @GetMapping("/course/category/{category}")
    @Operation(summary = "카테고리별 강의 목록 조회", description = "선택한 카테고리에 포함된 강의 목록을 조회합니다.")
    public ResponseEntity<List<CourseResponseDto>> getCoursesByCategory(
            @PathVariable String category
    ) {
        List<CourseResponseDto> courses = courseService.getCoursesByCategory(category);
        return ResponseEntity.ok(courses);
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
