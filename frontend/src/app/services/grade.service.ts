import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Enrollment, EnrollmentId } from './enrollment.service';

export interface Grade {
  id: EnrollmentId;
  enrollment: Enrollment;
  grade: number;
}

@Injectable({
  providedIn: 'root'
})
export class GradeService {
  private apiUrl = 'http://localhost:8080/api/grades';

  constructor(private http: HttpClient) {}

  assignGrade(studentId: number, courseId: number, gradeValue: number): Observable<Grade> {
    const params = new HttpParams().set('grade', gradeValue);
    return this.http.post<Grade>(`${this.apiUrl}/${studentId}/${courseId}`, null, { params });
  }

  getGrade(studentId: number, courseId: number): Observable<Grade> {
    return this.http.get<Grade>(`${this.apiUrl}/${studentId}/${courseId}`);
  }

  getAllGrades(): Observable<Grade[]> {
    return this.http.get<Grade[]>(this.apiUrl);
  }

  deleteGrade(studentId: number, courseId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${studentId}/${courseId}`);
  }
}
