package com.sparta.fifth_week_lv3.Service;
import com.sparta.fifth_week_lv3.dto.CourseDto;
import com.sparta.fifth_week_lv3.dto.LecturerDto;
import com.sparta.fifth_week_lv3.entity.Course;
import com.sparta.fifth_week_lv3.entity.Lecturer;
import com.sparta.fifth_week_lv3.repository.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class LecturerService {
    private final LecturerRepository lecturerRepository;

    @Autowired
    public LecturerService(LecturerRepository lecturerRepository) {
        this.lecturerRepository = lecturerRepository;
    }


    // 강사 등록
    public ResponseEntity<?> registerLecturer(Lecturer lecturer) {
        System.out.println("Career : " + lecturer.getCareer());
        // 강사 등록 중복 확인
        if (lecturerRepository.findByLecturer(lecturer.getLecturer()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 등록된 강사입니다.");
        }
        lecturerRepository.save(lecturer);
        return ResponseEntity.ok("강사 등록이 완료되었습니다.");
    }

//    public ResponseEntity<?> updateLecturerInfo(Long lecturerId, Lecturer updatedLecturer) {
//        // 해당 ID의 강사 찾기
//        Optional<Lecturer> optionalLecturer = lecturerRepository.findById(lecturerId);
//        if (!optionalLecturer.isPresent()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 강사를 찾을 수 없습니다.");
//        }
//
//        Lecturer lecturer = optionalLecturer.get();
//
//        // 수정된 정보 업데이트
//        if (updatedLecturer.getCareer() != null) {
//            lecturer.setCareer(updatedLecturer.getCareer());
//        }
//        if (updatedLecturer.getCompany() != null) {
//            lecturer.setCompany(updatedLecturer.getCompany());
//        }
//        if (updatedLecturer.getPhoneNumber() != null) {
//            lecturer.setPhoneNumber(updatedLecturer.getPhoneNumber());
//        }
//        if (updatedLecturer.getIntroduction() != null) {
//            lecturer.setIntroduction(updatedLecturer.getIntroduction());
//        }
//
//        // 수정된 강사 정보를 저장
//        lecturerRepository.save(lecturer);
//
//        return ResponseEntity.ok(lecturer);
//    }

    // 강사 조회
    public LecturerDto getLecturer(Long lecturerId) {
        Lecturer lecturer = lecturerRepository.findById(lecturerId).orElse(null);
        if (lecturer != null) {
            return toDto(lecturer);
        } else {
            return null;
        }
    }
    public LecturerDto toDto(Lecturer lecturer){
        return new LecturerDto(lecturer.getName(), lecturer.getCareer(), lecturer.getPhoneNumber(), lecturer.getIntroduction(), lecturer.getCompany(), lecturer.getLecturer());
    }

    // 선택 강사가 촬영한 강의 목록 조회
//    public List<CourseDto> getLecturerCourse(Long lecturerId){
//        Lecturer lecturer = lecturerRepository.findById(lecturerId)
//                .orElseThrow(() -> new RuntimeException("해당 강사를 찾을 수 없습니다."));
//        List<CourseDto> courseDtos = new ArrayList<>();
//        for (Course course : lecturer.getCourses()) {
//            LocalDateTime createdAt = LocalDateTime.now();
//            CourseDto dto = new CourseDto(
//                    course.getCourseName(),
//                   course.getPrice(),
//                   course.getCategory(),
//                   course.getLecturer(),
//                   createdAt
//            );
//            courseDtos.add(dto);
//        }
//        return courseDtos;
//    }

}
