import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Modal } from 'bootstrap';
import { University, UniversityService } from 'src/app/services/university.service';

@Component({
  selector: 'app-universities',
  templateUrl: './universities.component.html'
})
export class UniversitiesComponent implements OnInit {
  universities: University[] = [];
  form: University = { name: '' };

  @ViewChild('universityModal') universityModalRef!: ElementRef;
  @ViewChild('deleteModal') deleteModalRef!: ElementRef;

  private universityModal!: Modal;
  private deleteModal!: Modal;

  universityToDelete: University | null = null;

  constructor(private universityService: UniversityService) {}

  ngOnInit(): void {
    this.loadUniversities();
  }

  ngAfterViewInit(): void {
    this.universityModal = new Modal(this.universityModalRef.nativeElement);
    this.deleteModal = new Modal(this.deleteModalRef.nativeElement);
  }

  loadUniversities(): void {
    this.universityService.getAll().subscribe(data => {
      this.universities = data;
    });
  }

  openAddModal(): void {
    this.resetForm();
    this.universityModal.show();
  }

  openEditModal(university: University): void {
    this.form = { ...university };
    this.universityModal.show();
  }

  confirmDelete(university: University): void {
    this.universityToDelete = university;
    this.deleteModal.show();
  }

  deleteUniversity(): void {
    if (this.universityToDelete) {
      this.universityService.delete(this.universityToDelete.id!).subscribe(() => {
        this.loadUniversities();
        this.deleteModal.hide();
      });
    }
  }

  onSubmit(): void {
    if (this.form.id) {
      this.universityService.update(this.form).subscribe(() => {
        this.loadUniversities();
        this.universityModal.hide();
      });
    } else {
      this.universityService.add(this.form).subscribe(() => {
        this.loadUniversities();
        this.universityModal.hide();
      });
    }
  }

  resetForm(): void {
    this.form = { name: '' };
  }
}
