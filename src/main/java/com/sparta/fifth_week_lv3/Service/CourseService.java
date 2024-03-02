package com.sparta.fifth_week_lv3.Service;

import com.sparta.fifth_week_lv3.dto.CourseDto;
import com.sparta.fifth_week_lv3.entity.Course;
import com.sparta.fifth_week_lv3.repository.CourseRepository;
import jakarta.transaction.Transactional;
import lombok.Setter;
import org.apache.catalina.security.SecurityUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@Service
@Setter
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    // 강의 동록
    public Course registerCourse(CourseDto courseDto){
        Course course = new Course (
                courseDto.getCourseName(),
                courseDto.getPrice(),
                courseDto.getCategory(),
                courseDto.getLecturer(),
                LocalDateTime.now()
        );

        return courseRepository.save(course);
    }

    // 강의 수정
    public ResponseEntity<?> updateCourseInfo(Long courseId, Course updatedCourse) {
        // 해당 ID의 강의를 찾기
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if (optionalCourse.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 강의를 찾을 수 없습니다.");
        }

        Course course = optionalCourse.get();

        // 수정된 정보를 갱신
        course.setCourseName(updatedCourse.getCourseName());
        course.setPrice(updatedCourse.getPrice());
        course.setCategory(updatedCourse.getCategory());
        course.setLecturer(updatedCourse.getLecturer());

        // 수정된 강의 저장
        courseRepository.save(course);

        return ResponseEntity.ok(course);
    }

    // 선택한 강의 조회 기능
    public CourseDto getCourseCheck(Long id){
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isEmpty()){
            // 강의가 없는 경우
            return null;
        }

        Course course = optionalCourse.get();
        LocalDateTime createdAt = LocalDateTime.now();
        return new CourseDto(course.getCourseName(), course.getPrice(), course.getCategory(), course.getLecturer(), createdAt);
    }

    @Transactional
    public List<CourseDto> getCoursesByLecturerId(Long lecturerId) {
        System.out.println("강사 ID 확인: " + lecturerId); // 강사 ID 출력

        // 강사 id에 해당하는 강의 목록 조회
        List<Course> courses = courseRepository.findByLecturerId(lecturerId);

        // course 엔티티 -> courseDto -> 리스트
        return courses.stream()
                .map(course -> new CourseDto(course.getId(), course.getCourseName(), course.getPrice(), course.getCategory(), course.getLecturer(), course.getCreatedAt()))
                .collect(Collectors.toList());
    }
}