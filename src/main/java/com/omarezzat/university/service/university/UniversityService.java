package com.omarezzat.university.service.university;

import com.omarezzat.university.model.University;

import java.util.List;
import java.util.Optional;

public interface UniversityService {
    Optional<List<University>> getAllUniversities();
    Optional<University> findById(Long id);
    Optional<University> findByName(String name);
    void addUniversity(University university);
    void updateUniversity(University university);
    void deleteUniversity(Long id);
}
