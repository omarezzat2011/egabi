package com.omarezzat.university;

import com.omarezzat.university.controller.FacultyController;
import com.omarezzat.university.model.Faculty;
import com.omarezzat.university.service.Faculty.FacultyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class FacultyControllerTest {

    @Mock
    private FacultyService facultyService;

    @InjectMocks
    private FacultyController facultyController;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void getAll_shouldReturnFaculties() {
        List<Faculty> faculties = List.of(new Faculty(), new Faculty());
        when(facultyService.getAllFaculties()).thenReturn(faculties);

        ResponseEntity<List<Faculty>> response = facultyController.getAll();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(faculties, response.getBody());
    }

    @Test
    void getById_shouldReturnFaculty() {
        Faculty faculty = new Faculty();
        when(facultyService.findById(1L)).thenReturn(faculty);

        ResponseEntity<Faculty> response = facultyController.getById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(faculty, response.getBody());
    }

    @Test
    void add_shouldReturnCreated() {
        Faculty faculty = new Faculty();

        ResponseEntity<Void> response = facultyController.add(faculty);

        verify(facultyService).addFaculty(faculty);
        assertEquals(201, response.getStatusCodeValue());
    }

    @Test
    void update_shouldReturnNoContent() {
        Faculty faculty = new Faculty();

        ResponseEntity<Void> response = facultyController.update(faculty);

        verify(facultyService).updateFaculty(faculty);
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void delete_shouldReturnNoContent() {
        ResponseEntity<Void> response = facultyController.delete(1L);

        verify(facultyService).deleteFaculty(1L);
        assertEquals(204, response.getStatusCodeValue());
    }
}