package com.sparta.fifth_week_lv3.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "course")
public class Course extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String courseName;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String lecturerName;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    // 생성자
    public Course(String courseName, Double price, String category, String lecturerName) {
        this.courseName = courseName;
        this.price = price;
        this.category = category;
        this.lecturerName = lecturerName;
        this.createdAt=LocalDateTime.now();
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }
}
