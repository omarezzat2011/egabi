package com.omarezzat.university.service.Course;

import com.omarezzat.university.exception.NotFoundException;
import com.omarezzat.university.model.Course;
import com.omarezzat.university.model.Faculty;
import com.omarezzat.university.repository.CourseRepository;
import com.omarezzat.university.repository.FacultyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final FacultyRepository facultyRepository;

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Course not found"));
    }

    @Override
    public void addCourse(Course course) {
        if (!facultyRepository.existsById(course.getFaculty().getId())) {
            throw new NotFoundException("Faculty not found");
        }
        courseRepository.save(course);
    }

    @Override
    public void updateCourse(Course course) {
        if (!courseRepository.existsById(course.getId())) {
            throw new NotFoundException("Course not found");
        }
        courseRepository.save(course);
    }

    @Override
    @Transactional
    public void deleteCourse(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isEmpty()) {
            throw new NotFoundException("Course with Id: " + id + " Not Found");
        }
        courseRepository.deleteById(id);
    }
}
