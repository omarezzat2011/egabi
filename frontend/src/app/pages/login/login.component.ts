import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { ToastrService } from 'ngx-toastr';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent {
  username = '';
  password = '';
  role = 'admin';

  constructor(private authService: AuthService, private router: Router
    , private toastr: ToastrService
   ) {}

  login() {
    this.authService.login(this.username, this.password, this.role).subscribe({
      next: (response) => {
        console.log('Login successful:', response);
        localStorage.setItem('token', response.token);
        localStorage.setItem('role', this.role);
        if (this.role === 'admin') {
          this.router.navigate(['/universities']);
        } else {
          this.router.navigate(['/universities']); // later, create this route
        }
      },
      error: (err) => {
        // console.error('Login error:', err);
        // if (err.status === 401) {
        //   this.toastr.warning('Unauthorized: You are not allowed to perform this action.', '401 Unauthorized');
        // } else if (err.status === 403) {
        //   this.toastr.error('Invalid credentials. Please check your username and password.', '403 Forbidden');
        // } else {
        //   this.toastr.error('Something went wrong. Please try again.', 'Error');
        // }
      }
    });
  }
}
