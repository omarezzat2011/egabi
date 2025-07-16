import { Component, OnInit } from '@angular/core';
import { Enrollment, EnrollmentService } from 'src/app/services/enrollment.service';
import { Student, StudentService } from 'src/app/services/student.service';
import { Course, CourseService } from 'src/app/services/course.service';

@Component({
  selector: 'app-enrollments',
  templateUrl: './enrollments.component.html'
})
export class EnrollmentsComponent implements OnInit {
  enrollments: Enrollment[] = [];
  students: Student[] = [];
  courses: Course[] = [];
  selectedStudentId: number = 0;
  selectedCourseId: number = 0;

  constructor(
    private enrollmentService: EnrollmentService,
    private studentService: StudentService,
    private courseService: CourseService
  ) {}

  ngOnInit(): void {
    this.loadAll();
  }

  loadAll(): void {
    this.enrollmentService.getAll().subscribe(data => this.enrollments = data);
    this.studentService.getAll().subscribe(data => this.students = data);
    this.courseService.getAll().subscribe(data => this.courses = data);
  }

  enroll(): void {
    if (this.selectedStudentId && this.selectedCourseId) {
      this.enrollmentService.enroll(this.selectedStudentId, this.selectedCourseId).subscribe(() => {
        this.loadAll();
        this.selectedStudentId = 0;
        this.selectedCourseId = 0;
      });
    }
  }

  deleteEnrollment(studentId: number, courseId: number): void {
    this.enrollmentService.delete(studentId, courseId).subscribe(() => {
      this.loadAll();
    });
  }

  getStudentName(id: number): string {
    return this.students.find(s => s.id === id)?.name || 'Unknown';
  }

  getCourseName(id: number): string {
    return this.courses.find(c => c.id === id)?.name || 'Unknown';
  }
}
