package com.sparta.fifth_week_lv3.service;

import com.sparta.fifth_week_lv3.dto.admin.AdminRequestDto;
import com.sparta.fifth_week_lv3.dto.admin.AdminResponseDto;
import com.sparta.fifth_week_lv3.entity.Admin;
import com.sparta.fifth_week_lv3.entity.AdminRoleEnum;
import com.sparta.fifth_week_lv3.entity.DepartmentTypeEnum;
import com.sparta.fifth_week_lv3.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminResponseDto createAdmin(AdminRequestDto requestDto) {
        String email = requestDto.getEmail();
        String password = passwordEncoder.encode(requestDto.getPassword());
        DepartmentTypeEnum department = requestDto.getDepartment();
        AdminRoleEnum authority = requestDto.getAuthority();
        Admin convertedEntity = requestDto.toEntity(email, password, department, authority);
        Optional<Admin> findedAdmin = adminRepository.findByEmail(email);
        if (findedAdmin.isPresent()) {
            throw new IllegalArgumentException("중복된 이메일입니다.");

        }

        adminRepository.save(convertedEntity);
        AdminResponseDto responseDto = new AdminResponseDto(convertedEntity);
        return responseDto;


    }


}
