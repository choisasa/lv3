package com.sparta.fifth_week_lv3.dto.admin;

import com.sparta.fifth_week_lv3.entity.Admin;
import com.sparta.fifth_week_lv3.entity.AdminRoleEnum;
import com.sparta.fifth_week_lv3.entity.DepartmentTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class AdminRequestDto {
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    @NotBlank
    private String email;
    @NotBlank
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
