package com.omarezzat.university.service.Faculty;

import com.omarezzat.university.model.Faculty;

import java.util.List;

public interface FacultyService {
    List<Faculty> getAllFaculties();
    Faculty findById(Long id);
    void addFaculty(Faculty faculty);
    void updateFaculty(Faculty faculty);
}
