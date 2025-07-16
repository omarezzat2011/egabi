package com.omarezzat.university.service.Grade;

import com.omarezzat.university.exception.NotFoundException;
import com.omarezzat.university.model.Enrollment;
import com.omarezzat.university.model.EnrollmentId;
import com.omarezzat.university.model.Grade;
import com.omarezzat.university.repository.EnrollmentRepository;
import com.omarezzat.university.repository.GradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Grade grade = new Grade(enrollment, value);
        return gradeRepository.save(grade);
    }

    @Override
    public Grade getGrade(Long studentId, Long courseId) {
        EnrollmentId id = new EnrollmentId(studentId, courseId);
        return gradeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Grade not found"));
    }

    @Override
    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }

    @Override
    public void deleteGrade(Long studentId, Long courseId) {
        EnrollmentId id = new EnrollmentId(studentId, courseId);
        Optional<Grade> grade = gradeRepository.findById(id);
        if (grade.isEmpty()) {
            throw new NotFoundException("Enrollment with Id: " + id + " Not Found");
        }
        gradeRepository.deleteById(id);

    }
}
