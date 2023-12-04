import { Component, OnInit } from '@angular/core';
import { FormGroup,FormControl } from '@angular/forms';
import { ResultManagerService } from '../../services/result-manager.service';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {

  editResult = new FormGroup({
    rollno: new FormControl(''),
    name: new FormControl(''),
    dob: new FormControl(''),
    score: new FormControl(''),
  })

  constructor(private router:ActivatedRoute,private redirectrouter:Router, private result:ResultManagerService) { }

  ngOnInit(): void {
    console.log(this.router.snapshot.params['id'])
    this.result.getCurrentResult(this.router.snapshot.params['id']).subscribe((response:any)=>{
      this.editResult = new FormGroup({
        rollno: new FormControl(response['rollno']),
        name: new FormControl(response['name']),
        dob: new FormControl(response['dob']),
        score: new FormControl(response['score']),
      })
    })
  }

  patch(){
    this.result.editCurrentResult(this.router.snapshot.params['id'],this.editResult.value).subscribe((response)=>{
      console.log(response)
      this.redirectrouter.navigate(["/teacher/home"]);
    })
  }

}
