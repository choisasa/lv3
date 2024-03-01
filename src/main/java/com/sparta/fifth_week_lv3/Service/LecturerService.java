package com.sparta.fifth_week_lv3.Service;

import com.sparta.fifth_week_lv3.dto.LecturerDto;
import com.sparta.fifth_week_lv3.entity.Lecturer;
import com.sparta.fifth_week_lv3.repository.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class LecturerService {
    private final LecturerRepository lecturerRepository;

    @Autowired
    public LecturerService(LecturerRepository lecturerRepository) {
        this.lecturerRepository = lecturerRepository;
    }


    // 강사 등록
    public ResponseEntity<?> registerLecturer(Lecturer lecturer){
        // 강사 등록 중복 확인
        if(lecturerRepository.findByLecturer(lecturer.getLecturer()).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 등록된 강사입니다.");
        }
        lecturerRepository.save(lecturer);
        return ResponseEntity.ok("강사 등록이 완료되었습니다.");
    }

    public ResponseEntity<?> updateLecturerInfo(Long lecturerId, Lecturer updatedLecturer) {
        // 해당 ID의 강사 찾기
        Optional<Lecturer> optionalLecturer = lecturerRepository.findById(lecturerId);
        if (!optionalLecturer.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 강사를 찾을 수 없습니다.");
        }

        Lecturer lecturer = optionalLecturer.get();

        // 수정된 정보 업데이트
        if (updatedLecturer.getCareer() != null) {
            lecturer.setCareer(updatedLecturer.getCareer());
        }
        if (updatedLecturer.getCompany() != null) {
            lecturer.setCompany(updatedLecturer.getCompany());
        }
        if (updatedLecturer.getPhoneNumber() != null) {
            lecturer.setPhoneNumber(updatedLecturer.getPhoneNumber());
        }
        if (updatedLecturer.getIntroduction() != null) {
            lecturer.setIntroduction(updatedLecturer.getIntroduction());
        }

        // 수정된 강사 정보를 저장
        lecturerRepository.save(lecturer);

        return ResponseEntity.ok(lecturer);
    }

//    public ResponseEntity<?> getLecturerById(Long lecturerId){
//        Optional<Lecturer> optionalLecturer = lecturerRepository.findById(lecturerId);
//
//    }
}