import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddComponent } from './components/add/add.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { EditComponent } from './components/edit/edit.component';
import { HomeComponent } from './components/home/home.component';

const routes: Routes = [
  { path: '', component: DashboardComponent, children: [
    { path: 'add', component: AddComponent},
    { path: 'home', component: HomeComponent},
    { path: 'edit/:id', component: EditComponent},
    { path: '' , redirectTo: '/teacher/home', pathMatch: 'full' }
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TeacherRoutingModule { }
