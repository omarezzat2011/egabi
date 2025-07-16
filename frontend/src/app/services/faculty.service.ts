import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { University } from './university.service';

export interface Faculty {
  id?: number;
  name: string;
  university: {
  name: string;
    id: number;
  };
}


@Injectable({
  providedIn: 'root'
})
export class FacultyService {
  private apiUrl = 'http://localhost:8080/api/faculties';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Faculty[]> {
    return this.http.get<Faculty[]>(this.apiUrl);
  }

  getById(id: number): Observable<Faculty> {
    return this.http.get<Faculty>(`${this.apiUrl}/${id}`);
  }

  add(faculty: Faculty): Observable<void> {
    return this.http.post<void>(this.apiUrl, faculty);
  }

  update(faculty: Faculty): Observable<void> {
    return this.http.put<void>(this.apiUrl, faculty);
  }
  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
  
}
