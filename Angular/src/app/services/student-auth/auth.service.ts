import { Observable, of, throwError } from 'rxjs';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private router: Router, private http:HttpClient) { }
  setRole(role: string): void {
    localStorage.setItem('role', role);
  }

  getRole(): string | null {
    return localStorage.getItem('role');
  }

  isLoggedIn() {
    return this.getRole() === 'student';
  }

  logout() {
    localStorage.removeItem('role');
    this.router.navigate(['login']);
  }

  url="http://localhost:3000/student"
  result:any = []
  obj:any
  getCredentials(email:string){
    this.http.get(`${this.url}?email=${email}`).subscribe(res => {
      this.result = res
      this.obj = this.result[0]
    })
  }

  sleep(ms:any) {
    return new Promise(resolve => setTimeout(resolve, ms));
  }

  async login({ email, password }: any): Promise<Observable<any>> {
    this.getCredentials(email)
    await this.sleep(500)
    console.log(email,password)
    console.log(this.obj.email,this.obj.password)
    if (email == this.obj.email && password == this.obj.password) {
      this.setRole('student');
      var Name = this.obj.name
      this.obj = ''
      return of({ name: Name });
    } else {
      return throwError(new Error("Login Failed"));
    }
  }
}
