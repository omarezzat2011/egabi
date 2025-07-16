import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Faculty } from './faculty.service';

export interface Student {
  id?: number;
  name: string;
  level: number;
  nationalId: string;
  faculty: Faculty;
}

@Injectable({
  providedIn: 'root'
})
export class StudentService {
  private apiUrl = 'http://localhost:8080/api/students';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Student[]> {
    return this.http.get<Student[]>(this.apiUrl);
  }

  getById(id: number): Observable<Student> {
    return this.http.get<Student>(`${this.apiUrl}/${id}`);
  }

  add(student: Student): Observable<void> {
    return this.http.post<void>(this.apiUrl, student);
  }

  update(student: Student): Observable<void> {
    return this.http.put<void>(this.apiUrl, student);
  }
  
  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
  
}
