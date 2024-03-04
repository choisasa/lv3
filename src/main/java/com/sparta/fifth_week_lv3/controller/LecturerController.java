package com.sparta.fifth_week_lv3.controller;
import com.sparta.fifth_week_lv3.dto.lecturer.LecturerRequestDto;
import com.sparta.fifth_week_lv3.dto.lecturer.LecturerResponseDto;
import com.sparta.fifth_week_lv3.service.LecturerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@Slf4j(topic = "강사 등록, 수정, 조회")
@Tag(name = "강사 API", description = "강사 등록, 수정, 조회, 삭제")
@RestController
@RequiredArgsConstructor
public class LecturerController {
    private final LecturerService lecturerService;

    @Operation(summary = "강사 등록", description = "강사 등록시 사용할 정보를 입력합니다.")
    @Parameter(name = "JSON", description = "lecturerName, career, company, phoneNumber, introduction 필요")
    @PostMapping("/lecturer")
    public ResponseEntity<?> registerLecturer(@RequestBody LecturerRequestDto lecturerRequestDto) {
        LecturerResponseDto responseDto = lecturerService.registerLecturer(lecturerRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @Secured("ROLE_MANAGER")
    @Operation(summary = "강사 수정", description = "강사 수정시 사용할 정보를 입력합니다.")
    @Parameter(name = "JSON", description = "lecturerName, career, company, phoneNumber, introduction 필요")
    @PutMapping("/lecturer")
    public ResponseEntity<LecturerResponseDto> updateLecturerInfo(@RequestBody LecturerRequestDto lecturerRequestDto) {
        LecturerResponseDto responseDto = lecturerService.updateLecturerInfo(lecturerRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @Secured("ROLE_MANAGER")
    @Operation(summary = "강사 삭제", description = "강사 삭제시 사용할 정보를 입력합니다.")
    @Parameter(name = "JSON", description = "lecturerName 필요")
    @DeleteMapping("/lecturer")
    public ResponseEntity<LecturerResponseDto> deleteLecturerInfo(@RequestBody LecturerRequestDto lecturerRequestDto) {
        LecturerResponseDto responseDto = lecturerService.deleteLecturerInfo(lecturerRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
