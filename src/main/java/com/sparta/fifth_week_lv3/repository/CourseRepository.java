package com.sparta.fifth_week_lv3.repository;

import com.sparta.fifth_week_lv3.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}