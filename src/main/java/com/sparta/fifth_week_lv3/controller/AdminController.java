package com.sparta.fifth_week_lv3.controller;

import com.sparta.fifth_week_lv3.dto.AdminRequestDto;
import com.sparta.fifth_week_lv3.dto.AdminResponseDto;
import com.sparta.fifth_week_lv3.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "관리자 API", description = "관리자 등록")
@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @Operation(summary = "관리자 등록", description = "관리자 등록시 사용할 정보를 입력합니다.")
    @Parameter(name = "JSON", description = "email, password, department, authority 필요")
    @PostMapping("/admin")
    public ResponseEntity<AdminResponseDto> createAdmin(@RequestBody AdminRequestDto requestDto) {
        AdminResponseDto responseDto = adminService.createAdmin(requestDto);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
