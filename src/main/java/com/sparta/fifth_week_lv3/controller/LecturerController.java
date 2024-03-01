package com.sparta.fifth_week_lv3.controller;

import com.sparta.fifth_week_lv3.Service.LecturerService;
import com.sparta.fifth_week_lv3.dto.LecturerDto;
import com.sparta.fifth_week_lv3.entity.Lecturer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LecturerController {
    private final LecturerService lecturerService;

    // 강사 등록
    @PostMapping("/lecturer")
    public ResponseEntity<?> registerLecturer(@RequestBody LecturerDto lecturerDto){
        Lecturer lecturer = new Lecturer(
                lecturerDto.getName(),
        lecturerDto.getCareer(),
        lecturerDto.getCompany(),
        lecturerDto.getPhoneNumber(),
        lecturerDto.getIntroduction(),
                lecturerDto.getLecturer()
        );

        lecturerService.registerLecturer(lecturer);
        return ResponseEntity.ok("강사 등록이 완료되었습니다.");
    }

    // 강사정보 수정
    @PutMapping("/lecturer")
    public ResponseEntity<?> updateLecturerInfo(@PathVariable Long lecturerId, @RequestBody Lecturer updatedLecturer) {
        return lecturerService.updateLecturerInfo(lecturerId, updatedLecturer);
    }
}
