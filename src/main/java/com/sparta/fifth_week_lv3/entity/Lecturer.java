package com.sparta.fifth_week_lv3.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "lecturer")
public class Lecturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer career;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String introduction;

    private String lecturer;

    public Lecturer (String name, Integer career, String company, String phoneNumber, String introduction, String lecturer){
        this.name = name;
        this.career = career;
        this.company = company;
        this.phoneNumber = phoneNumber;
        this.introduction = introduction;
        this.lecturer = lecturer;
    }

    public Long getId(){return id;}

    public String getName(){return name;}

    public Integer getCareer(){return career;}
    public void setCareer(int career){this.career = career;}

    public String getCompany(){return company;}
    public void setCompany(String company){this.company = company;}

    public String getPhoneNumber(){return phoneNumber;}
    public void setPhoneNumber(String phoneNumber){this.phoneNumber = phoneNumber;}

    public String getIntroduction(){return introduction;}
    public void setIntroduction(String introduction){this.introduction = introduction;}

}
