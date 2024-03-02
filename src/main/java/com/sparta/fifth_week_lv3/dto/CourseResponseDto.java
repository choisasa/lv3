package com.sparta.fifth_week_lv3.dto;

import com.sparta.fifth_week_lv3.entity.Course;
import lombok.Getter;

@Getter
public class CourseResponseDto {
    private Long id;
    private String courseName;
    private Double price;
    private String category;
    private String lecturerName;

    public CourseResponseDto(Course course) {
        this.id = course.getId();
        this.courseName = course.getCourseName();
        this.price = course.getPrice();
        this.category = course.getCategory();
        this.lecturerName = course.getLecturerName();

    }


}