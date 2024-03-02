package com.sparta.fifth_week_lv3.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Table(name = "lecturer")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Lecturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String lecturerName;

    @Column(nullable = false)
    private Integer career;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String introduction;


    @Builder
    public Lecturer(String lecturerName, Integer career, String company, String phoneNumber, String introduction) {
        this.lecturerName = lecturerName;
        this.career = career;
        this.company = company;
        this.phoneNumber = phoneNumber;
        this.introduction = introduction;
    }


    public void update(int career, String company, String phoneNumber, String introduction) {
        this.career = career;
        this.company = company;
        this.phoneNumber = phoneNumber;
        this.introduction = introduction;
    }
}
