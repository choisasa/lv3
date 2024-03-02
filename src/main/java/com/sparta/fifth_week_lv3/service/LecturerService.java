package com.sparta.fifth_week_lv3.service;

import com.sparta.fifth_week_lv3.dto.lecturer.LecturerDto;
import com.sparta.fifth_week_lv3.dto.lecturer.LecturerResponseDto;
import com.sparta.fifth_week_lv3.entity.Lecturer;
import com.sparta.fifth_week_lv3.repository.LecturerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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

    @Transactional
    public LecturerResponseDto updateLecturerInfo(LecturerDto lecturerDto) {
        String lecturerName = lecturerDto.getLecturerName();
        int career = lecturerDto.getCareer();
        String company = lecturerDto.getCompany();
        String phoneNumber = lecturerDto.getPhoneNumber();
        String introduction = lecturerDto.getPhoneNumber();
        Lecturer lecturer = lecturerRepository.findByLecturerName(lecturerDto.getLecturerName()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강사입니다."));
        lecturer.update(career, company, phoneNumber, introduction);
        LecturerResponseDto responseDto = new LecturerResponseDto(lecturer);
        return responseDto;
    }


}