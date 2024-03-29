package com.sparta.fifth_week_lv3.service;

import com.sparta.fifth_week_lv3.dto.lecturer.LecturerRequestDto;
import com.sparta.fifth_week_lv3.dto.lecturer.LecturerResponseDto;
import com.sparta.fifth_week_lv3.entity.Lecturer;
import com.sparta.fifth_week_lv3.repository.LecturerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class LecturerService {
    private final LecturerRepository lecturerRepository;

    // 강사 등록
    public LecturerResponseDto registerLecturer(LecturerRequestDto lecturerRequestDto) {
        String lecturerName = lecturerRequestDto.getLecturerName();
        int career = lecturerRequestDto.getCareer();
        String company = lecturerRequestDto.getCompany();
        String phoneNumber = lecturerRequestDto.getPhoneNumber();
        String introduction = lecturerRequestDto.getPhoneNumber();
        Lecturer convertedEntity = lecturerRequestDto.toEntity(lecturerName, career, company, phoneNumber, introduction);
        // 강사 등록 중복 확인
        if (lecturerRepository.findByLecturerName(lecturerName).isPresent()) {
            throw new IllegalArgumentException("중복된 강사이름입니다.");
        }
        lecturerRepository.save(convertedEntity);
        LecturerResponseDto responseDto = new LecturerResponseDto(convertedEntity);
        return responseDto;
    }

    @Transactional
    public LecturerResponseDto updateLecturerInfo(LecturerRequestDto lecturerRequestDto) {
        String lecturerName = lecturerRequestDto.getLecturerName();
        int career = lecturerRequestDto.getCareer();
        String company = lecturerRequestDto.getCompany();
        String phoneNumber = lecturerRequestDto.getPhoneNumber();
        String introduction = lecturerRequestDto.getPhoneNumber();
        Lecturer lecturer = lecturerRepository.findByLecturerName(lecturerName).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강사입니다."));
        lecturer.update(career, company, phoneNumber, introduction);
        LecturerResponseDto responseDto = new LecturerResponseDto(lecturer);
        return responseDto;
    }

    @Transactional
    public LecturerResponseDto deleteLecturerInfo(LecturerRequestDto lecturerRequestDto) {
        String lecturerName = lecturerRequestDto.getLecturerName();
        Lecturer lecturer = lecturerRepository.findByLecturerName(lecturerName).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강사입니다."));
        lecturerRepository.delete(lecturer);
        LecturerResponseDto responseDto = new LecturerResponseDto(lecturer);
        return responseDto;
    }

    // 강사 정보 수정
    public LecturerResponseDto updateLecturerInfo(Long lecturerId, LecturerRequestDto updatedLecturerRequestDto) {
        Optional<Lecturer> optionalLecturer = lecturerRepository.findById(lecturerId);
        if (!optionalLecturer.isPresent()) {
            throw new IllegalArgumentException("해당 ID의 강사를 찾을 수 없습니다.");
        }

        Lecturer lecturer = optionalLecturer.get();

        // 수정된 정보 업데이트
        Integer updatedCareer = updatedLecturerRequestDto.getCareer();
        String updatedCompany = updatedLecturerRequestDto.getCompany();
        String updatedPhoneNumber = updatedLecturerRequestDto.getPhoneNumber();
        String updatedIntroduction = updatedLecturerRequestDto.getIntroduction();

        // 새로운 객체로 갱신
        Lecturer updatedLecturer = new Lecturer(
                lecturer.getLecturerName(),
                updatedCareer != null ? updatedCareer : lecturer.getCareer(),
                updatedCompany != null ? updatedCompany : lecturer.getCompany(),
                updatedPhoneNumber != null ? updatedPhoneNumber : lecturer.getPhoneNumber(),
                updatedIntroduction != null ? updatedIntroduction : lecturer.getIntroduction()
        );

        // 수정된 강사 정보를 저장
        lecturerRepository.save(updatedLecturer);

        return new LecturerResponseDto(updatedLecturer);
    }

    // 강사조회
    public LecturerResponseDto getLecturerByName(String lecturerName) {
        Optional<Lecturer> optionalLecturer = lecturerRepository.findByLecturerName(lecturerName);
        if (!optionalLecturer.isPresent()) {
            throw new IllegalArgumentException("해당 이름의 강사를 찾을 수 없습니다.");
        }

        Lecturer lecturer = optionalLecturer.get();
        return new LecturerResponseDto(lecturer);
    }

    // 선택한 강사가 촬영한 강의 목록 조회
    public List<LecturerResponseDto> getLecturesByLecturerName(String lecturerName) {
        return lecturerRepository.findByLecturerName(lecturerName).stream()
                .map(LecturerResponseDto::new)
                .collect(Collectors.toList());
    }

}