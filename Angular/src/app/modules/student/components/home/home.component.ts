import { Component, OnInit } from '@angular/core';
import { FormGroup,FormControl } from '@angular/forms';
import { SearchResultService } from '../../services/search-result.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  studentInfo = new FormGroup({
    rollno: new FormControl(''),
    name: new FormControl('')
  })

  constructor(private result:SearchResultService, private router:Router) { }

  ngOnInit(): void {
  }

  Search(){
    this.router.navigate([`/student/result/${this.studentInfo.value['rollno']}/${this.studentInfo.value['name']}`])
  }
}
