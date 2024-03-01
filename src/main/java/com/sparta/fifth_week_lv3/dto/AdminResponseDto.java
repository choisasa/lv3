package com.sparta.fifth_week_lv3.dto;

import com.sparta.fifth_week_lv3.entity.Admin;
import com.sparta.fifth_week_lv3.entity.AdminRoleEnum;
import com.sparta.fifth_week_lv3.entity.DepartmentTypeEnum;
import lombok.Getter;

@Getter
public class AdminResponseDto {
    private String email;
    private String password;
    private DepartmentTypeEnum department;
    private AdminRoleEnum authority;

    public AdminResponseDto(Admin admin) {
        this.email = admin.getEmail();
        this.password = admin.getPassword();
        this.department = admin.getDepartment();
        this.authority = admin.getAuthority();
    }
}
