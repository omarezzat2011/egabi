package com.omarezzat.university.repository;

import com.omarezzat.university.model.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UniversityRepository extends JpaRepository<University, Long> {
    Optional<University> findByName(String name);
    boolean existsByName(String name);
}
