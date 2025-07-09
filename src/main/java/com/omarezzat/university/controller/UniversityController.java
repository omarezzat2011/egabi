package com.omarezzat.university.controller;

import com.omarezzat.university.model.University;
import com.omarezzat.university.service.university.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/universities")
@RequiredArgsConstructor
public class UniversityController {

    private final UniversityService universityService;

    @GetMapping
    public ResponseEntity<List<University>> getAll() {
        return ResponseEntity.ok(universityService.getAllUniversities().orElse(List.of()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<University> getById(@PathVariable Long id) {
        return ResponseEntity.ok(universityService.findById(id).orElseThrow());
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody University university) {
        universityService.addUniversity(university);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody University university) {
        universityService.updateUniversity(university);
        return ResponseEntity.noContent().build();
    }
}

