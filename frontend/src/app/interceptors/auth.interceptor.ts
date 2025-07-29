import { Injectable } from '@angular/core';
import {
  HttpEvent,
  HttpInterceptor,
  HttpHandler,
  HttpRequest,
  HttpErrorResponse
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr'; // or your notification library

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private router: Router, private toastr: ToastrService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = localStorage.getItem('token');

    if (token) {
      req = req.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
    }

    return next.handle(req).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 401) {
          // Unauthorized (e.g., no token or expired)
          this.toastr.warning('Unauthorized: You are not allowed to perform this action.', '401 Unauthorized');
        } else if (error.status === 403) {
          // Forbidden (e.g., logged in but not allowed)
          this.toastr.error('Invalid credentials. Please check your username and password.', '403 Forbidden');
        } else {
          this.toastr.error('An unexpected error occurred.', 'Error');
        }

        return throwError(() => error);
      })
    );
  }
}
