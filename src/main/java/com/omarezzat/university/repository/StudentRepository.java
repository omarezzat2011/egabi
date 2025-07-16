package com.omarezzat.university.repository;

import com.omarezzat.university.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByNationalId(String nationalId);
}
