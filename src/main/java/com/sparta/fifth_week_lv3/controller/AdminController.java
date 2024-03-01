package com.sparta.fifth_week_lv3.controller;

import com.sparta.fifth_week_lv3.Service.AdminService;
import com.sparta.fifth_week_lv3.dto.AdminDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    // 관리자 등록
    @PostMapping("/admin")
    public ResponseEntity<?> registerAdmin(@RequestBody AdminDto adminDto){
        try {
            adminService.registerAdmin(adminDto);
            return new ResponseEntity<>("등록에 성광했습니다.", HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>("등록에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
