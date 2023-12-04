import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/student-auth/auth.service';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-student-login',
  templateUrl: './student-login.component.html',
  styleUrls: ['./student-login.component.css']
})
export class StudentLoginComponent implements OnInit {

  loginForm = new FormGroup({
    email: new FormControl(''),
    password: new FormControl('')
  });

  constructor(private auth: AuthService, private router: Router) { }

  ngOnInit(): void {
    if (this.auth.isLoggedIn()) {
      this.router.navigate(['student']);
    }
  }

  async onSubmit() {
    (await this.auth.login(this.loginForm.value)).subscribe((result: any) => {
      console.log(result);
      this.router.navigate(['/student']);
    },
      (err: Error) => {
        alert(err.message);
      }
    );
  }
}
