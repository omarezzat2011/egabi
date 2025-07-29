package com.omarezzat.university;

import com.omarezzat.university.controller.StudentController;
import com.omarezzat.university.exception.AlreadyExistsException;
import com.omarezzat.university.model.Student;
import com.omarezzat.university.service.Student.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void getAll_shouldReturnStudents() {
        List<Student> students = List.of(new Student(), new Student());
        when(studentService.getAllStudents()).thenReturn(students);

        ResponseEntity<List<Student>> response = studentController.getAll();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(students, response.getBody());
    }

    @Test
    void getById_shouldReturnStudent() {
        Student student = new Student();
        when(studentService.findById(1L)).thenReturn(student);

        ResponseEntity<Student> response = studentController.getById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(student, response.getBody());
    }

    @Test
    void add_shouldReturnCreated() {
        Student student = new Student();

        ResponseEntity<Void> response = studentController.add(student);

        verify(studentService).addStudent(student);
        assertEquals(201, response.getStatusCodeValue());
    }

    @Test
    void add_shouldReturnConflictOnDuplicate() {
        Student student = new Student();
        doThrow(AlreadyExistsException.class).when(studentService).addStudent(student);

        ResponseEntity<Void> response = studentController.add(student);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    void update_shouldReturnNoContent() {
        Student student = new Student();

        ResponseEntity<Void> response = studentController.update(student);

        verify(studentService).updateStudent(student);
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void delete_shouldReturnNoContent() {
        ResponseEntity<Void> response = studentController.delete(1L);

        verify(studentService).deleteStudent(1L);
        assertEquals(204, response.getStatusCodeValue());
    }
}