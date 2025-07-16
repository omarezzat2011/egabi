package com.omarezzat.university.service.Grade;

import com.omarezzat.university.model.Grade;

import java.util.List;

public interface GradeService {
    Grade assignGrade(Long studentId, Long courseId, double value);
    Grade getGrade(Long studentId, Long courseId);

    List<Grade> getAllGrades();

    void deleteGrade(Long studentId, Long courseId);
}
