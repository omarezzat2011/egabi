import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Modal } from 'bootstrap';
import { Student, StudentService } from 'src/app/services/student.service';
import { Faculty, FacultyService } from 'src/app/services/faculty.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html'
})
export class StudentsComponent implements OnInit {
  students: Student[] = [];
  faculties: Faculty[] = [];
  form: Student = { name: '', level: 1, nationalId: '', faculty: { id: 0, name: '', university: { id: 0 ,name :'' } } };

  @ViewChild('studentModal') studentModalRef!: ElementRef;
  @ViewChild('deleteModal') deleteModalRef!: ElementRef;

  private studentModal!: Modal;
  private deleteModal!: Modal;

  studentToDelete: Student | null = null;
  nationalIdInvalid: boolean = false;

  constructor(private studentService: StudentService, private facultyService: FacultyService) {}

  ngOnInit(): void {
    this.loadStudents();
    this.loadFaculties();
  }

  ngAfterViewInit(): void {
    this.studentModal = new Modal(this.studentModalRef.nativeElement);
    this.deleteModal = new Modal(this.deleteModalRef.nativeElement);
  }

  loadStudents(): void {
    this.studentService.getAll().subscribe(data => this.students = data);
  }

  loadFaculties(): void {
    this.facultyService.getAll().subscribe(data => this.faculties = data);
  }

  openAddModal(): void {
    this.resetForm();
    if (this.faculties.length > 0) {
      this.form.faculty.id = this.faculties[0].id!;
    }
    this.studentModal.show();
  }

  openEditModal(student: Student): void {
    this.form = { ...student };
    this.studentModal.show();
  }

  confirmDelete(student: Student): void {
    this.studentToDelete = student;
    this.deleteModal.show();
  }

  deleteStudent(): void {
    if (this.studentToDelete) {
      this.studentService.delete(this.studentToDelete.id!).subscribe(() => {
        this.loadStudents();
        this.deleteModal.hide();
      });
    }
  }

  validateNationalId(): void {
    this.nationalIdInvalid = this.form.nationalId.length !== 14;
  }

  onSubmit(): void {
    this.validateNationalId();
    if (this.nationalIdInvalid) return;

    const action = this.form.id ? this.studentService.update(this.form) : this.studentService.add(this.form);

    action.subscribe({
      next: () => {
        this.loadStudents();
        this.studentModal.hide();
      },
      error: (err: HttpErrorResponse) => {
        if (err.status === 409) {
          alert('Error: National ID already exists.');
        } else {
          console.error('Unexpected error:', err);
        }
      }
    });
  }

  resetForm(): void {
    this.form = { name: '', level: 1, nationalId: '', faculty: { id: 0, name: '', university: { id: 0 , name :'' } } };
    this.nationalIdInvalid = false;
  }
}
