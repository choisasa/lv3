package com.sparta.fifth_week_lv3.dto;

import com.sparta.fifth_week_lv3.entity.Admin;
import com.sparta.fifth_week_lv3.entity.AdminRoleEnum;
import com.sparta.fifth_week_lv3.entity.DepartmentTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class AdminRequestDto {
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "유효한 이메일 주소를 입력하세요.")
    @NotBlank
    private String email;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$", message = "비밀번호는 최소 8자 이상, 15자 이하이며 알파벳 대소문자, 숫자, 특수문자를 포함해야 합니다.")
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
