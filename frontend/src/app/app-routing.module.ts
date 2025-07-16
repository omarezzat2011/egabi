import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UniversitiesComponent } from './pages/universities/universities.component';
import { FacultiesComponent } from './pages/faculties/faculties.component';
import { CoursesComponent } from './pages/courses/courses.component';
import { StudentsComponent } from './pages/students/students.component';
import { EnrollmentsComponent } from './pages/enrollments/enrollments.component';
import { GradesComponent } from './pages/grades/grades.component';


const routes: Routes = [
  { path: '', redirectTo: 'universities', pathMatch: 'full' },
  { path: 'universities', component: UniversitiesComponent },
  { path: 'faculties', component: FacultiesComponent },
  { path: 'courses', component: CoursesComponent },
  { path: 'students', component: StudentsComponent },
  { path: 'enrollments', component: EnrollmentsComponent },
  { path: 'grades', component: GradesComponent }



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {}
