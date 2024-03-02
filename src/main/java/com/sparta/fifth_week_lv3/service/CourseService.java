package com.sparta.fifth_week_lv3.service;

import com.sparta.fifth_week_lv3.dto.CourseDto;
import com.sparta.fifth_week_lv3.dto.CourseResponseDto;
import com.sparta.fifth_week_lv3.entity.Course;
import com.sparta.fifth_week_lv3.repository.CourseRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // 강의 등록
    public CourseResponseDto registerCourse(CourseDto courseDto) {
        String courseName = courseDto.getCourseName();
        Double price = courseDto.getPrice();
        String category = courseDto.getCategory();
        String lecturerName = courseDto.getLecturerName();

        // Course 엔티티 생성자를 호출하여 createdAt을 설정
        Course course = new Course(courseName, price, category, lecturerName);

        // 저장된 Course 엔티티 정보를 CourseResponseDto로 변환하여 반환
        Course savedCourse = courseRepository.save(course);
        return new CourseResponseDto(savedCourse);
    }

    // 선택한 강의 조회
    public CourseResponseDto getCourseByName(String courseName) {
        Course course = courseRepository.findByCourseName(courseName)
                .orElseThrow(() -> new IllegalArgumentException("해당 이름의 강의를 찾을 수 없습니다."));
        return new CourseResponseDto(course);
    }

    // 카테고리별 강의 목록 조회
    public List<CourseResponseDto> getCoursesByCategory(String category) {
        List<Course> courses = courseRepository.findByCategoryOrderByCreatedAtDesc(category);
        return courses.stream()
                .map(CourseResponseDto::new)
                .collect(Collectors.toList());
    }
}

