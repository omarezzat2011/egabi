package com.omarezzat.university.service.Faculty;

import com.omarezzat.university.exception.NotFoundException;
import com.omarezzat.university.model.Faculty;
import com.omarezzat.university.repository.FacultyRepository;
import com.omarezzat.university.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService{

    private final FacultyRepository facultyRepository;
    private final UniversityRepository universityRepository;

    @Override
    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    @Override
    public Faculty findById(Long id) {
        return facultyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Faculty not found"));
    }

    @Override
    public void addFaculty(Faculty faculty) {
        if (!universityRepository.existsById(faculty.getUniversity().getId())) {
            throw new NotFoundException("University not found");
        }
        facultyRepository.save(faculty);
    }

    @Override
    public void updateFaculty(Faculty faculty) {
        if (!facultyRepository.existsById(faculty.getId())) {
            throw new NotFoundException("Faculty not found");
        }
        facultyRepository.save(faculty);
    }
}
