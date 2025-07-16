import { Component, OnInit } from '@angular/core';
import { Grade, GradeService } from 'src/app/services/grade.service';
import { EnrollmentService, Enrollment, EnrollmentId } from 'src/app/services/enrollment.service';
import { Student } from 'src/app/services/student.service';
import { Course } from 'src/app/services/course.service';

@Component({
  selector: 'app-grades',
  templateUrl: './grades.component.html'
})
export class GradesComponent implements OnInit {
  enrollments: Enrollment[] = [];
  grades: Grade[] = [];
  students: Student[] = [];
  filteredCourses: Course[] = [];

  selectedStudentId: number = 0;
  selectedCourseId: number = 0;
  gradeValue: number | null = null;

  editGradeValue: number | null = null;
  editingEnrollmentId: EnrollmentId | null = null;

  gradeInputs: { [key: string]: number } = {};


  constructor(
    private gradeService: GradeService,
    private enrollmentService: EnrollmentService
  ) {}

  ngOnInit(): void {
    this.loadEnrollments();
    this.loadGrades();
  }

  loadGrades(): void {
    this.gradeService.getAllGrades().subscribe(data => this.grades = data);
  }

  loadEnrollments(): void {
    this.enrollmentService.getAll().subscribe(data => {
      this.enrollments = data;
      this.students = data
        .map(e => e.student)
        .filter((s, index, arr) => arr.findIndex(x => x.id === s.id) === index);
    });
  }

  onStudentSelected(): void {
    const studentId = Number(this.selectedStudentId);
    this.filteredCourses = this.enrollments
      .filter(e => e.student.id === studentId)
      .map(e => e.course)
      .filter((c, index, arr) => arr.findIndex(x => x.id === c.id) === index);
    this.selectedCourseId = 0;
  }

  assignGrade(): void {
    if (this.selectedStudentId && this.selectedCourseId && this.gradeValue !== null) {
      this.gradeService.assignGrade(this.selectedStudentId, this.selectedCourseId, this.gradeValue)
        .subscribe(() => {
          this.loadEnrollments();
          this.loadGrades();
          this.resetForm();
        });
    }
  }

  assignGradeTo(enrollment: Enrollment): void {
    const key = `${enrollment.student.id}-${enrollment.course.id}`;
    const value = this.gradeInputs[key];
  
    if (value !== undefined && value !== null) {
      this.gradeService.assignGrade(enrollment.student.id!, enrollment.course.id!, value)
        .subscribe(() => {
          this.loadGrades();
          this.gradeInputs[key] = null!;
        });
    }
  }
  

  getGrade(enrollment: Enrollment): string {
    const grade = this.grades.find(g =>
      g.id.studentId === enrollment.student.id &&
      g.id.courseId === enrollment.course.id
    );
    return grade ? grade.grade.toString() : '-';
  }

  resetForm(): void {
    this.selectedStudentId = 0;
    this.selectedCourseId = 0;
    this.gradeValue = null;
    this.filteredCourses = [];
  }

  isGraded(enrollment: Enrollment): boolean {
    return !!this.grades.find(g =>
      g.id.studentId === enrollment.student.id &&
      g.id.courseId === enrollment.course.id
    );
  }

  startEdit(enrollment: Enrollment): void {
    const grade = this.grades.find(g =>
      g.id.studentId === enrollment.student.id &&
      g.id.courseId === enrollment.course.id
    );
    if (grade) {
      this.editingEnrollmentId = grade.id;
      this.editGradeValue = grade.grade;
    }
  }

  updateGrade(enrollment: Enrollment): void {
    if (this.editGradeValue !== null) {
      this.gradeService.assignGrade(enrollment.student.id!, enrollment.course.id!, this.editGradeValue!)
        .subscribe(() => {
          this.loadGrades();
          this.editingEnrollmentId = null;
          this.editGradeValue = null;
        });
    }
  }

  deleteGrade(enrollment: Enrollment): void {
    this.gradeService.deleteGrade(enrollment.student.id!, enrollment.course.id!)
      .subscribe(() => {
        this.loadGrades();
      });
  }
}
