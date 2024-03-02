package com.sparta.fifth_week_lv3.dto.lecturer;

import com.sparta.fifth_week_lv3.entity.Lecturer;
import lombok.Getter;

@Getter
public class LecturerDto {
    private String lecturerName;
    private int career;
    private String company;
    private String phoneNumber;
    private String introduction;

    public LecturerDto(String lecturerName, int career, String company, String phoneNumber, String introduction) {
        this.lecturerName = lecturerName;
        this.career = career;
        this.company = company;
        this.phoneNumber = phoneNumber;
        this.introduction = introduction;
    }

    public Lecturer toEntity(String lecturerName, int career, String company, String phoneNumber, String introduction) {
        return Lecturer.builder()
                .lecturerName(lecturerName)
                .career(career)
                .company(company)
                .phoneNumber(phoneNumber)
                .introduction(introduction)
                .build();
    }


}