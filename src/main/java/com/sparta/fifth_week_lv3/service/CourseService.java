package com.sparta.fifth_week_lv3.service;

import com.sparta.fifth_week_lv3.dto.CourseDto;
import com.sparta.fifth_week_lv3.dto.CourseResponseDto;
import com.sparta.fifth_week_lv3.entity.Course;
import com.sparta.fifth_week_lv3.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Setter
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    // 강의 동록
    public CourseResponseDto registerCourse(CourseDto courseDto) {
        String courseName = courseDto.getCourseName();
        double price = courseDto.getPrice();
        String category = courseDto.getCategory();
        String lecturerName = courseDto.getLecturerName();
        Course convertedEntity = courseDto.toEntity(courseName, price, category, lecturerName);

        if (courseRepository.findByCourseName(courseName).isPresent()) {
            throw new IllegalArgumentException("중복된 강의이름입니다.");
        }
        courseRepository.save(convertedEntity);
        CourseResponseDto responseDto = new CourseResponseDto(convertedEntity);
        return responseDto;

    }

    public CourseResponseDto getCourseByName(String courseName) {
        Course course = courseRepository.findByCourseName(courseName).orElseThrow(() -> new IllegalArgumentException("강의가 존재하지 않습니다."));
        CourseResponseDto responseDto = new CourseResponseDto(course);
        return responseDto;
    }

    public List<CourseResponseDto> getCoursesByCategory(String category) {
        return courseRepository.findByCategory(category).stream().map(CourseResponseDto::new).toList();
    }


    @Transactional
    public CourseResponseDto updateCourseInfo(CourseDto courseDto) {
        String courseName = courseDto.getCourseName();
        Double price = courseDto.getPrice();
        String category = courseDto.getCategory();
        Course course = courseRepository.findByCourseName(courseName).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강의입니다."));
        course.update(courseName, price, category);
        CourseResponseDto responseDto = new CourseResponseDto(course);
        return responseDto;
    }

    @Transactional
    public CourseResponseDto deleteCourseInfo(CourseDto courseDto) {
        String courseName = courseDto.getCourseName();
        Course course = courseRepository.findByCourseName(courseName).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강의입니다."));
        courseRepository.delete(course);
        CourseResponseDto responseDto = new CourseResponseDto(course);
        return responseDto;
    }
}