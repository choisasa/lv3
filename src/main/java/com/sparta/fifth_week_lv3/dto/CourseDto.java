package com.sparta.fifth_week_lv3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {
    private Long id;
    private String courseName;
    private Double price;
    private String category;
    private String lecturer;
    private LocalDateTime createdAt;

    public CourseDto(String courseName, Double price, String category, String lecturer, LocalDateTime createdAt){
        this.courseName = courseName;
        this.price = price;
        this.category = category;
        this.lecturer = lecturer;
        this.createdAt = createdAt;
    }
}

