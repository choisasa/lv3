package com.sparta.fifth_week_lv3.service;

import com.sparta.fifth_week_lv3.dto.lecturer.LecturerDto;
import com.sparta.fifth_week_lv3.dto.lecturer.LecturerResponseDto;
import com.sparta.fifth_week_lv3.entity.Lecturer;
import com.sparta.fifth_week_lv3.repository.LecturerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LecturerService {
    private final LecturerRepository lecturerRepository;

    // 강사 등록
    public LecturerResponseDto registerLecturer(LecturerDto lecturerDto) {
        String lecturerName = lecturerDto.getLecturerName();
        int career = lecturerDto.getCareer();
        String company = lecturerDto.getCompany();
        String phoneNumber = lecturerDto.getPhoneNumber();
        String introduction = lecturerDto.getPhoneNumber();
        Lecturer convertedEntity = lecturerDto.toEntity(lecturerName, career, company, phoneNumber, introduction);
        // 강사 등록 중복 확인
        if (lecturerRepository.findByLecturerName(lecturerDto.getLecturerName()).isPresent()) {
            throw new IllegalArgumentException("중복된 강사이름입니다.");
        }
        lecturerRepository.save(convertedEntity);
        LecturerResponseDto responseDto = new LecturerResponseDto(convertedEntity);
        return responseDto;
    }
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
//
////    public ResponseEntity<?> getLecturerById(Long lecturerId){
////        Optional<Lecturer> optionalLecturer = lecturerRepository.findById(lecturerId);
////
////    }
//}