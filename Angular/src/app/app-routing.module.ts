import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { StudentLoginComponent } from './components/student-login/student-login.component';
import { TeacherLoginComponent } from './components/teacher-login/teacher-login.component';
import { AuthGuard } from './guards/auth.guard';
import { StudentAuthGuard } from './guards/student-auth.guard';

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  {path: 'teacher-login', component: TeacherLoginComponent},
  {path: 'student-login', component: StudentLoginComponent},
  {path: 'teacher', canActivate: [AuthGuard], loadChildren: () => import('./modules/teacher/teacher.module').then((m) => m.TeacherModule)},
  {path: 'student', canActivate: [StudentAuthGuard], loadChildren: () => import('./modules/student/student.module').then((n) => n.StudentModule)},
  {path: '**', component: NotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
