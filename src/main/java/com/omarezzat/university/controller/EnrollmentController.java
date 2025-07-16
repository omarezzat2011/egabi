package com.omarezzat.university.controller;

import com.omarezzat.university.model.Enrollment;
import com.omarezzat.university.model.EnrollmentId;
import com.omarezzat.university.service.Enrollment.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @GetMapping
    public ResponseEntity<List<Enrollment>> getAll() {
        return ResponseEntity.ok(enrollmentService.getAllEnrollments());
    }

    @PostMapping("/{studentId}/{courseId}")
    public ResponseEntity<Enrollment> enroll(
            @PathVariable Long studentId,
            @PathVariable Long courseId) {
        return ResponseEntity.ok(enrollmentService.enrollStudent(studentId, courseId));
    }

    @GetMapping("/{studentId}/{courseId}")
    public ResponseEntity<Enrollment> getEnrollment(
            @PathVariable Long studentId,
            @PathVariable Long courseId) {
        return ResponseEntity.ok(enrollmentService.findEnrollment(studentId, courseId));
    }
    @DeleteMapping("/{studentId}/{courseId}")
    public ResponseEntity<Void> delete(@PathVariable Long studentId,@PathVariable Long courseId) {
        enrollmentService.deleteEnrollment(studentId,courseId);
        return ResponseEntity.noContent().build();
    }
}
