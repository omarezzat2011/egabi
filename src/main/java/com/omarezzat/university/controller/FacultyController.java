package com.omarezzat.university.controller;

import com.omarezzat.university.model.Faculty;
import com.omarezzat.university.service.Faculty.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faculties")
@RequiredArgsConstructor
public class FacultyController {

    private final FacultyService facultyService;

    @GetMapping
    public ResponseEntity<List<Faculty>> getAll() {
        return ResponseEntity.ok(facultyService.getAllFaculties());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faculty> getById(@PathVariable Long id) {
        return ResponseEntity.ok(facultyService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody Faculty faculty) {
        facultyService.addFaculty(faculty);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody Faculty faculty) {
        facultyService.updateFaculty(faculty);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.noContent().build();
    }
}

