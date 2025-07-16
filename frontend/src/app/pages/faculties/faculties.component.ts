  import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
  import { Modal } from 'bootstrap';
  import { Faculty, FacultyService } from 'src/app/services/faculty.service';
  import { University, UniversityService } from 'src/app/services/university.service';

  @Component({
    selector: 'app-faculties',
    templateUrl: './faculties.component.html'
  })
  export class FacultiesComponent implements OnInit {
    faculties: Faculty[] = [];
    universities: University[] = [];
    form: Faculty = { name: '', university: {
      id: 0,
      name: ''
    } };

    @ViewChild('facultyModal') facultyModalRef!: ElementRef;
    @ViewChild('deleteModal') deleteModalRef!: ElementRef;

    private facultyModal!: Modal;
    private deleteModal!: Modal;

    facultyToDelete: Faculty | null = null;

    constructor(private facultyService: FacultyService, private universityService: UniversityService) {}

    ngOnInit(): void {
      this.loadFaculties();
      this.loadUniversities();
    }

    ngAfterViewInit(): void {
      this.facultyModal = new Modal(this.facultyModalRef.nativeElement);
      this.deleteModal = new Modal(this.deleteModalRef.nativeElement);
    }

    loadFaculties(): void {
      this.facultyService.getAll().subscribe(data => this.faculties = data);
    }

    loadUniversities(): void {
      this.universityService.getAll().subscribe(data => this.universities = data);
    }

    openAddModal(): void {
      this.resetForm();
      if (this.universities.length > 0) {
        this.form.university.id = this.universities[0].id ?? 0 ;
      }
      this.facultyModal.show();
    }

    openEditModal(faculty: Faculty): void {
      this.form = { ...faculty };
      this.facultyModal.show();
    }

    confirmDelete(faculty: Faculty): void {
      this.facultyToDelete = faculty;
      this.deleteModal.show();
    }

    deleteFaculty(): void {
      if (this.facultyToDelete) {
        this.facultyService.delete(this.facultyToDelete.id!).subscribe(() => {
          this.loadFaculties();
          this.deleteModal.hide();
        });
      }
    }

    onSubmit(): void {
      if (this.form.id) {
        this.facultyService.update(this.form).subscribe(() => {
          this.loadFaculties();
          this.facultyModal.hide();
        });
      } else {
        this.facultyService.add(this.form).subscribe(() => {
          this.loadFaculties();
          this.facultyModal.hide();
        });
      }
    }

    resetForm(): void {
      this.form = { name: '', university: { id: 0 , name:'' } };
    }
  }
