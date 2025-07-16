package com.omarezzat.university.service.Student;

import com.omarezzat.university.exception.AlreadyExistsException;
import com.omarezzat.university.exception.NotFoundException;
import com.omarezzat.university.model.Student;
import com.omarezzat.university.repository.FacultyRepository;
import com.omarezzat.university.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student not found"));
    }

    @Override
    public void addStudent(Student student) {
        if (studentRepository.existsByNationalId(student.getNationalId())) {
            throw new AlreadyExistsException("Student Already Exists");
        }
        studentRepository.save(student);
    }

    @Override
    public void updateStudent(Student student) {
        if (!studentRepository.existsById(student.getId())) {
            throw new NotFoundException("Student not found");
        }
        studentRepository.save(student);
    }

    @Override
    @Transactional
    public void deleteStudent(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()) {
            throw new NotFoundException("Student with Id: " + id + " Not Found");
        }
        studentRepository.deleteById(id);
    }
}

