package com.sparta.fifth_week_lv3.dto;

import com.sparta.fifth_week_lv3.entity.Admin;
import com.sparta.fifth_week_lv3.entity.AdminRoleEnum;
import com.sparta.fifth_week_lv3.entity.DepartmentTypeEnum;
import lombok.Getter;

@Getter
public class AdminRequestDto {
    private String email;
    private String password;
    private DepartmentTypeEnum department;
    private AdminRoleEnum authority;

    public Admin toEntity(String email, String password, DepartmentTypeEnum department, AdminRoleEnum authority) {
        return Admin.builder()
                .email(email)
                .password(password)
                .department(department)
                .authority(authority)
                .build();

    }


}
