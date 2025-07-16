package com.omarezzat.university.service.Enrollment;

import com.omarezzat.university.exception.NotFoundException;
import com.omarezzat.university.model.Course;
import com.omarezzat.university.model.Enrollment;
import com.omarezzat.university.model.EnrollmentId;
import com.omarezzat.university.model.Student;
import com.omarezzat.university.repository.CourseRepository;
import com.omarezzat.university.repository.EnrollmentRepository;
import com.omarezzat.university.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Override
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    @Override
    public Enrollment enrollStudent(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course not found"));

        Enrollment enrollment = new Enrollment(student, course);
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public Enrollment findEnrollment(Long studentId, Long courseId) {
        EnrollmentId id = new EnrollmentId(studentId, courseId);
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Enrollment not found"));
    }

    @Override
    @Transactional
    public void deleteEnrollment(Long studentId, Long courseId) {
        EnrollmentId id = new EnrollmentId(studentId, courseId);
        Optional<Enrollment> enrollment = enrollmentRepository.findById(id);
        if (enrollment.isEmpty()) {
            throw new NotFoundException("Enrollment with Id: " + id + " Not Found");
        }
        enrollmentRepository.deleteById(id);
    }
}

