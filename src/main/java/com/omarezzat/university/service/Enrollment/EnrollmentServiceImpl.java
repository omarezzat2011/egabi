package com.omarezzat.university.service.Enrollment;

import com.omarezzat.university.exception.NotFoundException;
import com.omarezzat.university.model.Course;
import com.omarezzat.university.model.Enrollment;
import com.omarezzat.university.model.EnrollmentId;
import com.omarezzat.university.model.Student;
import com.omarezzat.university.repository.CourseRepository;
import com.omarezzat.university.repository.EnrollmentRepository;
import com.omarezzat.university.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
