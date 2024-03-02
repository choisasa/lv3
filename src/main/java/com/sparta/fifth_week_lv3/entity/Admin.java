package com.sparta.fifth_week_lv3.entity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "admin")
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private DepartmentTypeEnum department;
    @Enumerated(EnumType.STRING)
    private AdminRoleEnum authority;

    @Builder
    public Admin(String email, String password, DepartmentTypeEnum department, AdminRoleEnum authority) {
        this.email = email;
        this.password = password;
        this.department = department;
        this.authority = authority;
    }
}
