package com.omarezzat.university.service.Grade;

import com.omarezzat.university.model.Grade;

public interface GradeService {
    Grade assignGrade(Long studentId, Long courseId, double value);
    Grade getGrade(Long studentId, Long courseId);
}
