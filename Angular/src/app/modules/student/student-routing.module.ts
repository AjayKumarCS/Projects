import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { HomeComponent } from './components/home/home.component';
import { ResultComponent } from './components/result/result.component';

const routes: Routes = [
  { path: '', component: DashboardComponent, children: [
    { path: 'home', component: HomeComponent },
    { path: 'result/:rollno/:name', component: ResultComponent },
    { path: '', redirectTo: '/student/home', pathMatch: 'full' }
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StudentRoutingModule { }
