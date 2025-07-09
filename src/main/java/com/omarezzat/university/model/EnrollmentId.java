package com.omarezzat.university.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@EqualsAndHashCode
//@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentId implements Serializable {
    private Long studentId;
    private Long courseId;
    public EnrollmentId(Long studentId, Long courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }
}
