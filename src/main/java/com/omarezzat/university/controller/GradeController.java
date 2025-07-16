package com.omarezzat.university.controller;

import com.omarezzat.university.model.Grade;
import com.omarezzat.university.service.Grade.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
@RequiredArgsConstructor
public class GradeController {

    private final GradeService gradeService;


    @PostMapping("/{studentId}/{courseId}")
    public ResponseEntity<Grade> assignGrade(
            @PathVariable Long studentId,
            @PathVariable Long courseId,
            @RequestParam double grade) {
        return ResponseEntity.ok(gradeService.assignGrade(studentId, courseId, grade));
    }

    @GetMapping("/{studentId}/{courseId}")
    public ResponseEntity<Grade> getGrade(
            @PathVariable Long studentId,
            @PathVariable Long courseId) {
        return ResponseEntity.ok(gradeService.getGrade(studentId, courseId));
    }

    @GetMapping()
    public ResponseEntity<List<Grade>> getAllGrades() {
        return ResponseEntity.ok(gradeService.getAllGrades());
    }
    @DeleteMapping("/{studentId}/{courseId}")
    public ResponseEntity<Void> delete(@PathVariable Long studentId,@PathVariable Long courseId) {
        gradeService.deleteGrade(studentId,courseId);
        return ResponseEntity.noContent().build();
    }
}

