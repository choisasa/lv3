package com.sparta.fifth_week_lv3.repository;

import com.sparta.fifth_week_lv3.entity.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Long> {
    Optional<Lecturer> findByLecturerName(String lecturerName);
   // List<Lecturer> findByOrderByIdDesc(String lecturerName);

}
