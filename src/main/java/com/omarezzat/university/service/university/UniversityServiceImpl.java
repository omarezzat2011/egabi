package com.omarezzat.university.service.university;

import com.omarezzat.university.exception.AlreadyExistsException;
import com.omarezzat.university.exception.NotFoundException;
import com.omarezzat.university.model.University;
import com.omarezzat.university.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UniversityServiceImpl implements UniversityService {
    private final UniversityRepository universityRepository;
    @Override
    public Optional<List<University>> getAllUniversities() {
        return Optional.of(universityRepository.findAll());
    }

    @Override
    public Optional<University> findById(Long id) {
        return Optional.of(universityRepository.findById(id)).orElseThrow(()-> new NotFoundException("University not found"));
    }

    @Override
    public Optional<University> findByName(String name) {
        return Optional.of(universityRepository.findByName(name)).orElseThrow(() -> new NotFoundException("University with Name: "+name+" Not Found"));
    }

    @Override
    public void addUniversity(University university) {
        if(universityRepository.existsByName(university.getName()))
            throw new AlreadyExistsException("University with Name: "+university.getName()+" Already Exists");
        universityRepository.save(university);
    }

    @Override
    public void updateUniversity(University university) {
//        Optional<University> existingUniversity = universityRepository.findById(university.getId()).
        if(! universityRepository.existsById(university.getId()))
            throw new NotFoundException("University with Id: "+university.getId()+" Not Found");
        if(universityRepository.existsByName(university.getName()))
            throw new AlreadyExistsException("University with Name: "+university.getName()+" Already Exists");

//        universityRepository.up;
    }

    @Override
    public void deleteUniversity(Long id) {

    }
}
