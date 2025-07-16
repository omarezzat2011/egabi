import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Student } from './student.service';
import { Course } from './course.service';

export interface EnrollmentId {
  studentId: number;
  courseId: number;
}

export interface Enrollment {
  id: EnrollmentId;
  student: Student;
  course: Course;
  enrollmentDate: string;
}

@Injectable({
  providedIn: 'root'
})
export class EnrollmentService {
  private apiUrl = 'http://localhost:8080/api/enrollments';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Enrollment[]> {
    return this.http.get<Enrollment[]>(this.apiUrl);
  }

  enroll(studentId: number, courseId: number): Observable<Enrollment> {
    console.log(studentId)
    console.log(courseId)
    return this.http.post<Enrollment>(`${this.apiUrl}/${studentId}/${courseId}`, {});
  }

  getEnrollment(studentId: number, courseId: number): Observable<Enrollment> {
    return this.http.get<Enrollment>(`${this.apiUrl}/${studentId}/${courseId}`);
  }delete(studentId: number, courseId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${studentId}/${courseId}`);
  }
  

}
