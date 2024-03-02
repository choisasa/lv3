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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @Operation(summary = "강사 정보 수정", description = "선택한 강사의 정보를 수정합니다.")
    @Parameter(name = "JSON", description = "lecturerName, career, company, phoneNumber, introduction 필요")
    @PutMapping("/lecturer/{id}")
    public ResponseEntity<LecturerResponseDto> updateLecturerInfo(
            @PathVariable Long id,
            @RequestBody LecturerDto lecturerDto
    ) {

        LecturerResponseDto updatedLecturer = lecturerService.updateLecturerInfo(id, lecturerDto);
        return ResponseEntity.ok(updatedLecturer);
    }

    // 강사 조회
    @Operation(summary = "강사 조회", description = "선택한 강사의 정보를 조회합니다.")
    @GetMapping(value = "/lecturer/{lecturerName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LecturerResponseDto> getLecturer(
            @PathVariable String lecturerName
    ) {
        LecturerResponseDto lecturer = lecturerService.getLecturerByName(lecturerName);
        return ResponseEntity.ok(lecturer);
    }

    // 선택한 강사가 촬영한 강의 조회
    @GetMapping("/lecturer/course/{lecturerName}")
    @Operation(summary = "선택한 강사가 촬영한 강의 목록 조회", description = "선택한 강사가 촬영한 강의 목록을 조회합니다.")
    public ResponseEntity<List<LecturerResponseDto>> getLecturesByLecturerName(
            @PathVariable String lecturerName
    ) {
        List<LecturerResponseDto> lectureList = lecturerService.getLecturesByLecturerName(lecturerName);
        return lectureList.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(lectureList);
    }
}
