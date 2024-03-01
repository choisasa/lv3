package com.sparta.fifth_week_lv3.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
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
    private String lecturer;

//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(nullable = false)
//    private Date createdAt;

    // 생성자
    public Course(String courseName, Double price, String category, String lecturer) {
        this.courseName = courseName;
        this.price = price;
        this.category = category;
        this.lecturer = lecturer;
       // this.createdAt = createdAt;
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
}
