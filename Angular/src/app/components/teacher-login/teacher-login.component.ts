import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth/auth.service';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-teacher-login',
  templateUrl: './teacher-login.component.html',
  styleUrls: ['./teacher-login.component.css']
})
export class TeacherLoginComponent implements OnInit {

  loginForm = new FormGroup({
    email: new FormControl(''),
    password: new FormControl('')
  });

  constructor(private auth: AuthService, private router: Router) { }

  ngOnInit(): void {
    if (this.auth.isLoggedIn()) {
      this.router.navigate(['teacher']);
    }
  }

  async onSubmit() {
    (await this.auth.login(this.loginForm.value)).subscribe((result: any) => {
      console.log(result);
      this.router.navigate(['/teacher']);
    },
      (err: Error) => {
        alert(err.message);
      }
    );
  }
}


