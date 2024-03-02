package com.sparta.fifth_week_lv3.controller;

import com.sparta.fifth_week_lv3.dto.lecturer.LecturerDto;
import com.sparta.fifth_week_lv3.dto.lecturer.LecturerResponseDto;
import com.sparta.fifth_week_lv3.service.LecturerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j(topic = "강사 등록 및 로그인")
@Tag(name = "강사 API", description = "강사 등록 및 로그인")
@RestController
@RequiredArgsConstructor
public class LecturerController {
    private final LecturerService lecturerService;

    // 강사 등록
    @Operation(summary = "강사 등록", description = "강사 등록시 사용할 정보를 입력합니다.")
    @Parameter(name = "JSON", description = "lecturerName, career, company, phoneNumber, introduction 필요")
    @PostMapping("/lecturer")
    public ResponseEntity<?> registerLecturer(@RequestBody LecturerDto lecturerDto) {
        LecturerResponseDto responseDto = lecturerService.registerLecturer(lecturerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    // 강사정보 수정
//    @PutMapping("/lecturer")
//    public ResponseEntity<?> updateLecturerInfo(@PathVariable Long lecturerId, @RequestBody Lecturer updatedLecturer) {
//        return lecturerService.updateLecturerInfo(lecturerId, updatedLecturer);
//    }
}
