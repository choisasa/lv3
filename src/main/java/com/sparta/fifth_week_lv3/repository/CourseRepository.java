package com.sparta.fifth_week_lv3.repository;

import com.sparta.fifth_week_lv3.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByCourseName(String courseName);

    List<Course> findByCategory(String category);
    List<Course> findByLecturerName(String lecturerName);

}
