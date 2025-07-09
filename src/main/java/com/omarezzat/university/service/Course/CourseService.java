package com.omarezzat.university.service.Course;

import com.omarezzat.university.model.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();
    Course findById(Long id);
    void addCourse(Course course);
    void updateCourse(Course course);
}
