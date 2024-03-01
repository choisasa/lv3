package com.sparta.fifth_week_lv3.Service;

import com.sparta.fifth_week_lv3.dto.CourseDto;
import com.sparta.fifth_week_lv3.entity.Course;
import com.sparta.fifth_week_lv3.repository.CourseRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


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
        Course course = new Course(
                courseDto.getCourseName(),
                courseDto.getPrice(),
                courseDto.getCategory(),
                courseDto.getLecturer()
                //new Date()
        );

        return courseRepository.save(course);
    }

    // 강의 수정
    public ResponseEntity<?> updateCourseInfo(Long courseId, Course updatedCourse) {
        // 해당 ID의 강의를 찾습니다.
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if (!optionalCourse.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 강의를 찾을 수 없습니다.");
        }

        Course course = optionalCourse.get();

        // 수정된 정보를 갱신합니다.
        if (updatedCourse.getCourseName() != null) {
            course.setCourseName(updatedCourse.getCourseName());
        }
        if (updatedCourse.getPrice() != null) {
            course.setPrice(updatedCourse.getPrice());
        }
        if (updatedCourse.getCategory() != null) {
            course.setCategory(updatedCourse.getCategory());
        }
        if (updatedCourse.getLecturer() != null) {
            course.setLecturer(updatedCourse.getLecturer());
        }

        // 수정된 강의 저장
        courseRepository.save(course);

        return ResponseEntity.ok(course);
    }
}
