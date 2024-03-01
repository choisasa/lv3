package com.sparta.fifth_week_lv3.dto;

import com.sparta.fifth_week_lv3.entity.Lecturer;
import lombok.Getter;

@Getter
public class LecturerDto {
    private String name;
    private int career;
    private String company;
    private String phoneNumber;
    private String introduction;
    private String lecturer;
    public LecturerDto(String name, int career, String company, String phoneNumber, String introduction, String lecturer) {
        this.name = name;
        this.career = career;
        this.company = company;
        this.phoneNumber = phoneNumber;
        this.introduction = introduction;
        this.lecturer = lecturer;
    }

    public String getName() {
        return name;
    }

    public int getCareer() {
        return career;
    }

    public String getCompany() {
        return company;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getIntroduction() {
        return introduction;
    }

    public class LecturerUpdateDto {
        private Integer career; // 경력(년차)
        private String company; // 회사
        private String phoneNumber; // 전화번호
        private String introduction; // 소개

        // 생성자
        public LecturerUpdateDto(Integer career, String company, String phoneNumber, String introduction) {
            this.career = career;
            this.company = company;
            this.phoneNumber = phoneNumber;
            this.introduction = introduction;
        }

        // 각 필드의 게터
        public Integer getCareer() {
            return career;
        }

        public String getCompany() {
            return company;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getIntroduction() {
            return introduction;
        }


        // 강사 정보 업데이트
        public void updateInformation(Lecturer lecturer){
            if(this.career != null){
                lecturer.setCareer(this.career);
            }
        }
    }
}