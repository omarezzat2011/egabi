package com.omarezzat.university.service.Student;

import com.omarezzat.university.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student findById(Long id);
    void addStudent(Student student);
    void updateStudent(Student student);

    void deleteStudent(Long id);
}
