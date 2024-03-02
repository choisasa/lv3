package com.sparta.fifth_week_lv3.Service;

import com.sparta.fifth_week_lv3.dto.AdminDto;
import com.sparta.fifth_week_lv3.entity.Admin;
import com.sparta.fifth_week_lv3.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    @Autowired
    private AdminService(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }

    // admin 등록
    public void registerAdmin(AdminDto adminDto){
        // dto -> entity 변환해주기
        Admin admin = new Admin(
                adminDto.getEmail(),
                adminDto.getPassword(),
                adminDto.getDepartment(),
                adminDto.getAuthority()
        );
        adminRepository.save(admin);
    }

    // admin 업데이트
}
