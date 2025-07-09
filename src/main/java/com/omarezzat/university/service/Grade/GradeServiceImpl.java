package com.omarezzat.university.service.Grade;

import com.omarezzat.university.exception.NotFoundException;
import com.omarezzat.university.model.Enrollment;
import com.omarezzat.university.model.EnrollmentId;
import com.omarezzat.university.model.Grade;
import com.omarezzat.university.repository.EnrollmentRepository;
import com.omarezzat.university.repository.GradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;
    private final EnrollmentRepository enrollmentRepository;

    @Override
    public Grade assignGrade(Long studentId, Long courseId, double value) {
        EnrollmentId id = new EnrollmentId(studentId, courseId);
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Enrollment not found"));

        Grade grade = new Grade();
        grade.setId(id);
        grade.setEnrollment(enrollment);
        grade.setGrade(value);

        return gradeRepository.save(grade);
    }

    @Override
    public Grade getGrade(Long studentId, Long courseId) {
        EnrollmentId id = new EnrollmentId(studentId, courseId);
        return gradeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Grade not found"));
    }
}
