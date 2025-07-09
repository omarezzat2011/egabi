package com.omarezzat.university.repository;

import com.omarezzat.university.model.EnrollmentId;
import com.omarezzat.university.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GradeRepository extends JpaRepository<Grade, EnrollmentId> {
    Optional<Grade> findById(EnrollmentId enrollmentId);
}
