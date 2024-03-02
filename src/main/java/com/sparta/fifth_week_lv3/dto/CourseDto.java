package com.sparta.fifth_week_lv3.dto;

import com.sparta.fifth_week_lv3.entity.Course;
import lombok.Getter;

@Getter
public class CourseDto {
    private Long id;
    private String courseName;
    private Double price;
    private String category;
    private String lecturerName;

    public Course toEntity(String courseName, double price, String category, String lecturerName) {
        return Course.builder()
                .courseName(courseName)
                .price(price)
                .category(category)
                .lecturerName(lecturerName)
                .build();

    }
}
