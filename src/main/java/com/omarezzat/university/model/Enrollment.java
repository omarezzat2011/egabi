package com.omarezzat.university.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Enrollment {
    @EmbeddedId
    private EnrollmentId id;

    @ManyToOne()
    @MapsId("studentId")
    private Student student;

    @ManyToOne()
    @MapsId("courseId")
    private Course course;

    private LocalDate enrollmentDate;
    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.enrollmentDate = LocalDate.now();
        this.id = new EnrollmentId(student.getId(), course.getId());

    }




}
