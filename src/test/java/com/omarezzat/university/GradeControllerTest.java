package com.omarezzat.university;

import com.omarezzat.university.controller.GradeController;
import com.omarezzat.university.model.Grade;
import com.omarezzat.university.service.Grade.GradeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class GradeControllerTest {

    @Mock
    private GradeService gradeService;

    @InjectMocks
    private GradeController gradeController;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void assignGrade_shouldReturnGrade() {
        Grade grade = new Grade();
        when(gradeService.assignGrade(1L, 2L, 90.0)).thenReturn(grade);

        ResponseEntity<Grade> response = gradeController.assignGrade(1L, 2L, 90.0);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(grade, response.getBody());
    }

    @Test
    void getGrade_shouldReturnGrade() {
        Grade grade = new Grade();
        when(gradeService.getGrade(1L, 2L)).thenReturn(grade);

        ResponseEntity<Grade> response = gradeController.getGrade(1L, 2L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(grade, response.getBody());
    }

    @Test
    void getAllGrades_shouldReturnGrades() {
        List<Grade> grades = List.of(new Grade(), new Grade());
        when(gradeService.getAllGrades()).thenReturn(grades);

        ResponseEntity<List<Grade>> response = gradeController.getAllGrades();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(grades, response.getBody());
    }

    @Test
    void delete_shouldReturnNoContent() {
        ResponseEntity<Void> response = gradeController.delete(1L, 2L);

        verify(gradeService).deleteGrade(1L, 2L);
        assertEquals(204, response.getStatusCodeValue());
    }
}