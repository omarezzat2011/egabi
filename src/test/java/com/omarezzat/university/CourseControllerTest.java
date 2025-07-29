package com.omarezzat.university;

import com.omarezzat.university.controller.CourseController;
import com.omarezzat.university.model.Course;
import com.omarezzat.university.repository.CourseRepository;
import com.omarezzat.university.service.Course.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class CourseControllerTest {

    @Mock
    private CourseService courseService;

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseController courseController;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void getAll_shouldReturnCourses() {
        List<Course> courses = Arrays.asList(new Course(1L, "Math"), new Course(2L, "Physics"));
        when(courseService.getAllCourses()).thenReturn(courses);

        ResponseEntity<List<Course>> response = courseController.getAll();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(courses, response.getBody());
    }

    @Test
    void getById_shouldReturnCourse() {
        Course course = new Course(1L, "Math");
        when(courseService.findById(1L)).thenReturn(course);

        ResponseEntity<Course> response = courseController.getById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(course, response.getBody());
    }

    @Test
    void add_shouldCallServiceAndReturnCreated() {
        Course course = new Course(1L, "Biology");

        ResponseEntity<Void> response = courseController.add(course);

        verify(courseService, times(1)).addCourse(course);
        assertEquals(201, response.getStatusCodeValue());
    }

    @Test
    void update_shouldCallServiceAndReturnNoContent() {
        Course course = new Course(1L, "Chemistry");

        ResponseEntity<Void> response = courseController.update(course);

        verify(courseService, times(1)).updateCourse(course);
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void delete_shouldCallServiceAndReturnNoContent() {
        ResponseEntity<Void> response = courseController.delete(1L);

        verify(courseService, times(1)).deleteCourse(1L);
        assertEquals(204, response.getStatusCodeValue());
    }
}
