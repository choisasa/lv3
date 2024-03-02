package com.sparta.fifth_week_lv3.controller;

import com.sparta.fifth_week_lv3.SecurityUtils;
import com.sparta.fifth_week_lv3.Service.CourseService;
import com.sparta.fifth_week_lv3.Service.LecturerService;
import com.sparta.fifth_week_lv3.dto.CourseDto;
import com.sparta.fifth_week_lv3.dto.LecturerDto;
import com.sparta.fifth_week_lv3.entity.Lecturer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class LecturerController {
    private final LecturerService lecturerService;
    private final CourseService courseService;

    // 강사 등록
    @PostMapping("/lecturer")
    public ResponseEntity<?> registerLecturer(@RequestBody LecturerDto lecturerDto) {
//        LocalDateTime createdAt = LocalDateTime.now();
        Lecturer lecturer = new Lecturer(
                lecturerDto.getName(),
                lecturerDto.getCareer(),
                lecturerDto.getCompany(),
                lecturerDto.getPhoneNumber(),
                lecturerDto.getIntroduction(),
                lecturerDto.getLecturer()
//                lecturerDto.getCreateBy(),
//                createdAt
        );

        lecturerService.registerLecturer(lecturer);
        return ResponseEntity.ok("강사 등록이 완료되었습니다.");
    }
//
//    // 강사정보 수정
//    @PutMapping("/lecturer/{lecturerId}")
//    public ResponseEntity<?> updateLecturerInfo(@PathVariable Long lecturerId, @RequestBody Lecturer updatedLecturer) {
//        return lecturerService.updateLecturerInfo(lecturerId, updatedLecturer);
//    }

    // 강사 조회
    @GetMapping("/lecturer/{lecturerId}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<LecturerDto> getLecturer(@PathVariable Long lecturerId) {
        LecturerDto lecturerDto = lecturerService.getLecturer(lecturerId);
        if (lecturerDto != null) {
            return ResponseEntity.ok(lecturerDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 선택한 강사가 촬영한 강의 목록 조회
    @GetMapping("/lecturer/course/{id}")
    public ResponseEntity<?> getLecturerCourses(@PathVariable Long lecturerId) {
//        // JWT 토큰을 이용하여 사용자 역할 확인
//        if (!SecurityUtils.isAdmin(token)) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("관리자만 접근 가능합니다.");
//        }

        // 선택한 강사가 촬영한 강의 목록 조회s
        System.out.println("lecturer Id" + lecturerId);
        List<CourseDto> lecturerCourses = courseService.getCoursesByLecturerId(lecturerId);
        if (lecturerCourses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("선택한 강사가 촬영한 강의가 없습니다.");
        }

        // 등록일 기준으로 내림차순 정렬
        lecturerCourses.sort((c1, c2) -> c2.getCreatedAt().compareTo(c1.getCreatedAt()));

        return ResponseEntity.ok(lecturerCourses);
    }

    // 카테고리별
}