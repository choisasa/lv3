package com.sparta.fifth_week_lv3.controller;

import com.sparta.fifth_week_lv3.dto.CourseDto;
import com.sparta.fifth_week_lv3.dto.CourseResponseDto;
import com.sparta.fifth_week_lv3.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @Parameter(name = "JSON", description = "courseName, price, category, lecturerName 필요")
    public ResponseEntity<CourseResponseDto> registerCourse(@RequestBody CourseDto courseDto) {
        CourseResponseDto responseDto = courseService.registerCourse(courseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    // 강의 조회
    @GetMapping("/course/{courseName}")
    @Parameter(name = "String", description = "courseName 필요")
    @Operation(summary = "강의 이름으로 강의 조회", description = "선택한 강의 이름으로 강의 정보를 조회합니다.")
    public ResponseEntity<CourseResponseDto> getCourseByName(
            @PathVariable String courseName
    ) {
        CourseResponseDto responseDto = courseService.getCourseByName(courseName);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    // 카테고리별 강의 목록 조회
    @GetMapping("/course/course/{category}")
    @Parameter(name = "String", description = "category 필요")
    @Operation(summary = "카테고리별 강의 목록 조회", description = "선택한 카테고리에 포함된 강의 목록을 조회합니다.")
    public ResponseEntity<List<CourseResponseDto>> getCoursesByCategory(
            @PathVariable String category
    ) {
        List<CourseResponseDto> responseDtos = courseService.getCoursesByCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body(responseDtos);
    }


}
