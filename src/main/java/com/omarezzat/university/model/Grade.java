package com.omarezzat.university.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Grade {

    @EmbeddedId
    private EnrollmentId id;

    @MapsId("studentId") // link studentId in @EmbeddedId
    @JoinColumn(name = "student_id")
    @ManyToOne
    private Student student;

    @MapsId("courseId") // link courseId in @EmbeddedId
    @JoinColumn(name = "course_id")
    @ManyToOne
    private Course course;

    @Column(nullable = false)
    private double grade;

    public Grade(Enrollment enrollment, double grade) {
        this.id = new EnrollmentId(
                enrollment.getStudent().getId(),
                enrollment.getCourse().getId()
        );
        this.student = enrollment.getStudent();
        this.course = enrollment.getCourse();
        this.grade = grade;
    }

}
