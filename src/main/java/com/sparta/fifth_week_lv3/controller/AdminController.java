package com.sparta.fifth_week_lv3.controller;
import com.sparta.fifth_week_lv3.dto.admin.AdminRequestDto;
import com.sparta.fifth_week_lv3.dto.admin.AdminResponseDto;
import com.sparta.fifth_week_lv3.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j(topic = "관리자 등록 및 로그인")
@Tag(name = "관리자 API", description = "관리자 등록 및 로그인")
@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @Operation(summary = "관리자 등록", description = "관리자 등록시 사용할 정보를 입력합니다.")
    @Parameter(name = "JSON", description = "email, password, department, authority 필요")
    @PostMapping("/admin")
    public ResponseEntity<?> createAdmin(@Valid @RequestBody AdminRequestDto requestDto, BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if (fieldErrors.size() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
        }

        try {
            AdminResponseDto responseDto = adminService.createAdmin(requestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        } catch (Exception e) {
            log.error("관리자 등록 중 에러 발생: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "관리자 로그인", description = "관리자 로그인 사용할 정보를 입력합니다.")
    @Parameter(name = "JSON", description = "email, password 필요")
    @PostMapping("/admin/login")
    public ResponseEntity<?> login(@Valid @RequestBody AdminRequestDto requestDto, BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if (fieldErrors.size() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
        }
        try {
            AdminResponseDto responseDto = new AdminResponseDto(requestDto.toEntity(requestDto.getEmail(), requestDto.getPassword(), requestDto.getDepartment(), requestDto.getAuthority()));
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        } catch (Exception e) {
            log.error("관리자 등록 중 에러 발생: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
