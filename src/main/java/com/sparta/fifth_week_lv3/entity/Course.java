package com.sparta.fifth_week_lv3.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "course")
public class Course {

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

//    @Column(columnDefinition = "TIMESTAMP")
//    private LocalDateTime createdAt;

    // 생성자
    @Builder
    public Course(String courseName, Double price, String category, String lecturerName) {
        this.courseName = courseName;
        this.price = price;
        this.category = category;
        this.lecturerName = lecturerName;
    }

    public void update(String courseName, Double price, String category) {
        this.courseName = courseName;
        this.price = price;
        this.category = category;
        this.lecturerName = lecturerName;

    }

}
