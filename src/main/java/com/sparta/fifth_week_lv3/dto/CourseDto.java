package com.sparta.fifth_week_lv3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class CourseDto {
    private String courseName;
    private double price;
    private String category;
    private String lecturer;
}

