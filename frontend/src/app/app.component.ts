import { Component } from '@angular/core';
import { AuthService } from './services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'my-angular-app';
  constructor(public authService: AuthService, private router: Router) {}
  
  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']); // or your login route
  }
}
