package com.sparta.fifth_week_lv3.dto;

import com.sparta.fifth_week_lv3.entity.Lecturer;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class LecturerDto {
    private String name;
    private int career;
    private String company;
    private String phoneNumber;
    private String introduction;
    private String lecturer;
//    private String createBy;
//    private LocalDateTime createdAt;
    // CourseDto 객체를 저장할 리스트
    private List<CourseDto> courses = new ArrayList<>();

    public LecturerDto(String name, int career, String company, String phoneNumber, String introduction, String lecturer) {
        this.name = name;
        this.career = career;
        this.company = company;
        this.phoneNumber = phoneNumber;
        this.introduction = introduction;
        this.lecturer = lecturer;
//        this.createBy = createBy;
//        this.createdAt = createdAt;
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

//    public String getCreateBy() {
//        return createBy;
//    }
//
//    public LocalDateTime getCreatedAt() {return  createdAt;}

    // CourseDto 객체를 추가하는 메서드
    public void addCourse(CourseDto courseDto) {
        courses.add(courseDto);
    }

    // CourseDto 객체를 제거하는 메서드
    public void removeCourse(CourseDto courseDto) {
        courses.remove(courseDto);
    }

    // 모든 CourseDto 객체를 반환하는 메서드
    public List<CourseDto> getCourses(){
        return courses;
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


//        // 강사 정보 업데이트
//        public void updateInformation(Lecturer lecturer) {
//            if (this.career != null) {
//                lecturer.setCareer(this.career);
//            }
//        }

    }

}