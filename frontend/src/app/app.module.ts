import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { UniversitiesComponent } from './pages/universities/universities.component';
import { AppRoutingModule } from './app-routing.module';
import { FacultiesComponent } from './pages/faculties/faculties.component';
import { CoursesComponent } from './pages/courses/courses.component';
import { StudentsComponent } from './pages/students/students.component';
import { EnrollmentsComponent } from './pages/enrollments/enrollments.component';
import { GradesComponent } from './pages/grades/grades.component';
import { LoginComponent } from './pages/login/login.component';

import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthInterceptor } from './interceptors/auth.interceptor'; // adjust path if needed
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { ToastrModule } from 'ngx-toastr';

@NgModule({
  declarations: [AppComponent, UniversitiesComponent, FacultiesComponent, CoursesComponent, StudentsComponent, EnrollmentsComponent, GradesComponent, LoginComponent, ],
  imports: [BrowserModule, FormsModule, HttpClientModule, AppRoutingModule,    BrowserAnimationsModule,    ToastrModule.forRoot()  ],
  providers: [ {
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule {}
