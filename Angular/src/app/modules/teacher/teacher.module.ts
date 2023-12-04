import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TeacherRoutingModule } from './teacher-routing.module';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { HeaderComponent } from './components/header/header.component';
import { AddComponent } from './components/add/add.component';
import { HomeComponent } from './components/home/home.component';
import { ReactiveFormsModule } from '@angular/forms';
import { EditComponent } from './components/edit/edit.component';


@NgModule({
  declarations: [
    DashboardComponent,
    HeaderComponent,
    AddComponent,
    HomeComponent,
    EditComponent
  ],
  imports: [
    CommonModule,
    TeacherRoutingModule,
    ReactiveFormsModule
  ]
})
export class TeacherModule { }
