package com.sparta.fifth_week_lv3.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
    private String lecturer;

    @Column(nullable = false)
    private Long lecturerId;

//    @ManyToOne
//    @JoinColumn(name = "lecturerId", nullable = false)
//    private Lecturer lecturer;


    @Column(nullable = false)
    private LocalDateTime createdAt;

    // 생성자
    public Course(String courseName, Double price, String category, String lecturer, LocalDateTime createdAt) {
        this.courseName = courseName;
        this.price = price;
        this.category = category;
        this.lecturer = lecturer;
        this.createdAt = createdAt;
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

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}
}