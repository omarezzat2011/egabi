package com.omarezzat.university.repository;

import com.omarezzat.university.model.Faculty;
import com.omarezzat.university.model.University;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
//    @Override
    void deleteAllByUniversity(University university);
}
