package com.omarezzat.university.service.Enrollment;

import com.omarezzat.university.model.Enrollment;
import com.omarezzat.university.model.EnrollmentId;

import java.util.List;

public interface EnrollmentService {
    List<Enrollment> getAllEnrollments();
    Enrollment enrollStudent(Long studentId, Long courseId);
    Enrollment findEnrollment(Long studentId, Long courseId);


    void deleteEnrollment(Long studentId, Long courseId);

}

