package com.sparta.fifth_week_lv3.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String department;

    @Column(nullable = false)
    private String authority;

    public Admin(String email, String password, String department, String authority){
        this.email = email;
        this.password = password;
        this.department = department;
        this.authority = authority;
    }
    // Getter 메서드
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getDepartment() {
        return department;
    }

    public String getAuthority() {
        return authority;
    }
}