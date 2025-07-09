package com.omarezzat.university.repository;

import com.omarezzat.university.model.Enrollment;
import com.omarezzat.university.model.EnrollmentId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentId> {
    Optional<Enrollment> findById(EnrollmentId enrollmentId);
}
