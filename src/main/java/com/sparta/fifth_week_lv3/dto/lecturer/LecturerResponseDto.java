package com.sparta.fifth_week_lv3.dto.lecturer;

import com.sparta.fifth_week_lv3.entity.Lecturer;
import lombok.Getter;

@Getter
public class LecturerResponseDto {
    private String lecturerName;
    private Integer career;
    private String company;
    private String phoneNumber;
    private String introduction;

    public LecturerResponseDto(Lecturer lecturer) {
        this.lecturerName = lecturer.getLecturerName();
        this.career = lecturer.getCareer();
        this.company = lecturer.getCompany();
        this.phoneNumber = lecturer.getPhoneNumber();
        this.introduction = lecturer.getIntroduction();
    }
}
