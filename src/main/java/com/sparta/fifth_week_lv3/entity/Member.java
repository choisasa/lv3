package com.sparta.fifth_week_lv3.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Member
{
    private String email;
    private String password;
    private String department;
    private String permission;
}
