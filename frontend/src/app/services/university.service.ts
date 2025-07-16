import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface University {
  id?: number;
  name: string;
}

@Injectable({
  providedIn: 'root'
})
export class UniversityService {
  private apiUrl = 'http://localhost:8080/api/universities';

  constructor(private http: HttpClient) {}

  getAll(): Observable<University[]> {
    return this.http.get<University[]>(this.apiUrl);
  }

  getById(id: number): Observable<University> {
    return this.http.get<University>(`${this.apiUrl}/${id}`);
  }

  add(university: University): Observable<void> {
    return this.http.post<void>(this.apiUrl, university);
  }

  update(university: University): Observable<void> {
    return this.http.put<void>(this.apiUrl, university);
  }
  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
  
}
