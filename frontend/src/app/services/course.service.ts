import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Faculty } from './faculty.service';

export interface Course {
  id?: number;
  name: string;
  level: number;
  faculty: Faculty;
}

@Injectable({
  providedIn: 'root'
})
export class CourseService {
  private apiUrl = 'http://localhost:8080/api/courses';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Course[]> {
    return this.http.get<Course[]>(this.apiUrl);
  }

  getById(id: number): Observable<Course> {
    return this.http.get<Course>(`${this.apiUrl}/${id}`);
  }

  add(course: Course): Observable<void> {
    return this.http.post<void>(this.apiUrl, course);
  }

  update(course: Course): Observable<void> {
    return this.http.put<void>(this.apiUrl, course);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
