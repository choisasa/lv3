package com.sparta.fifth_week_lv3.dto;
import com.sparta.fifth_week_lv3.entity.Course;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {
    private Long id;
    private String courseName;
    private Double price;
    private String category;
    private String lecturerName;

    public Course toEntity() {
        Course course = new Course();
        course.setCourseName(this.courseName);
        course.setPrice(this.price != null ? this.price.doubleValue() : null);
        course.setCategory(this.category);
        course.setLecturerName(this.lecturerName);
        return course;
    }
}
