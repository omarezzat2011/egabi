import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Modal } from 'bootstrap';
import { Course, CourseService } from 'src/app/services/course.service';
import { Faculty, FacultyService } from 'src/app/services/faculty.service';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html'
})
export class CoursesComponent implements OnInit {
  courses: Course[] = [];
  faculties: Faculty[] = [];
  form: Course = { name: '', level: 1, faculty: { id: 0, name: '', university: { id: 0 , name : '' } } };

  @ViewChild('courseModal') courseModalRef!: ElementRef;
  @ViewChild('deleteModal') deleteModalRef!: ElementRef;

  private courseModal!: Modal;
  private deleteModal!: Modal;

  courseToDelete: Course | null = null;

  constructor(private courseService: CourseService, private facultyService: FacultyService) {}

  ngOnInit(): void {
    this.loadCourses();
    this.loadFaculties();
  }

  ngAfterViewInit(): void {
    this.courseModal = new Modal(this.courseModalRef.nativeElement);
    this.deleteModal = new Modal(this.deleteModalRef.nativeElement);
  }

  loadCourses(): void {
    this.courseService.getAll().subscribe(data => this.courses = data);
  }

  loadFaculties(): void {
    this.facultyService.getAll().subscribe(data => this.faculties = data);
  }

  openAddModal(): void {
    this.resetForm();
    if (this.faculties.length > 0) {
      this.form.faculty.id = this.faculties[0].id!;
    }
    this.courseModal.show();
  }

  openEditModal(course: Course): void {
    this.form = { ...course };
    this.courseModal.show();
  }

  confirmDelete(course: Course): void {
    this.courseToDelete = course;
    this.deleteModal.show();
  }

  deleteCourse(): void {
    if (this.courseToDelete) {
      this.courseService.delete(this.courseToDelete.id!).subscribe(() => {
        this.loadCourses();
        this.deleteModal.hide();
      });
    }
  }

  onSubmit(): void {
    if (this.form.id) {
      this.courseService.update(this.form).subscribe(() => {
        this.loadCourses();
        this.courseModal.hide();
      });
    } else {
      this.courseService.add(this.form).subscribe(() => {
        this.loadCourses();
        this.courseModal.hide();
      });
    }
  }

  resetForm(): void {
    this.form = { name: '', level: 1, faculty: { id: 0, name: '', university: { id: 0 , name :'' } } };
  }
}
