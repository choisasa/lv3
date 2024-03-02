package com.sparta.fifth_week_lv3.dto.lecturer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sparta.fifth_week_lv3.entity.Lecturer;
import lombok.Getter;

@Getter
public class LecturerResponseDto {
    @JsonProperty("lecturerName")
    private String lecturerName;
    @JsonProperty("career")
    private Integer career;
    @JsonProperty("company")
    private String company;
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    @JsonProperty("introduction")
    private String introduction;

    public LecturerResponseDto(Lecturer lecturer) {
        this.lecturerName = lecturer.getLecturerName();
        this.career = lecturer.getCareer();
        this.company = lecturer.getCompany();
        this.phoneNumber = lecturer.getPhoneNumber();
        this.introduction = lecturer.getIntroduction();
    }
}
