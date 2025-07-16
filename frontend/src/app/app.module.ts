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

@NgModule({
  declarations: [AppComponent, UniversitiesComponent, FacultiesComponent, CoursesComponent, StudentsComponent, EnrollmentsComponent, GradesComponent, ],
  imports: [BrowserModule, FormsModule, HttpClientModule, AppRoutingModule],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
