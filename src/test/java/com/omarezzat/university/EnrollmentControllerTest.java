package com.omarezzat.university;

import com.omarezzat.university.controller.EnrollmentController;
import com.omarezzat.university.model.Enrollment;
import com.omarezzat.university.service.Enrollment.EnrollmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class EnrollmentControllerTest {

    @Mock
    private EnrollmentService enrollmentService;

    @InjectMocks
    private EnrollmentController enrollmentController;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void getAll_shouldReturnEnrollments() {
        List<Enrollment> enrollments = List.of(new Enrollment(), new Enrollment());
        when(enrollmentService.getAllEnrollments()).thenReturn(enrollments);

        ResponseEntity<List<Enrollment>> response = enrollmentController.getAll();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(enrollments, response.getBody());
    }

    @Test
    void enroll_shouldReturnEnrollment() {
        Enrollment enrollment = new Enrollment();
        when(enrollmentService.enrollStudent(1L, 2L)).thenReturn(enrollment);

        ResponseEntity<Enrollment> response = enrollmentController.enroll(1L, 2L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(enrollment, response.getBody());
    }

    @Test
    void getEnrollment_shouldReturnEnrollment() {
        Enrollment enrollment = new Enrollment();
        when(enrollmentService.findEnrollment(1L, 2L)).thenReturn(enrollment);

        ResponseEntity<Enrollment> response = enrollmentController.getEnrollment(1L, 2L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(enrollment, response.getBody());
    }

    @Test
    void delete_shouldReturnNoContent() {
        ResponseEntity<Void> response = enrollmentController.delete(1L, 2L);

        verify(enrollmentService).deleteEnrollment(1L, 2L);
        assertEquals(204, response.getStatusCodeValue());
    }
}