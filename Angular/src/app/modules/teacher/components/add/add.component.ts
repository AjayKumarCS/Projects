import { Component, OnInit } from '@angular/core';
import { FormGroup,FormControl } from '@angular/forms';
import { ResultManagerService } from '../../services/result-manager.service';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {

  alert:boolean=false
  
  addResult = new FormGroup({
    rollno: new FormControl(''),
    name: new FormControl(''),
    dob: new FormControl(''),
    score: new FormControl(''),
  })
  constructor(private result:ResultManagerService) { }

  ngOnInit(): void {
  }

  postResult(){
    this.result.saveResult(this.addResult.value).subscribe((response)=>{
      console.log(response)
    })
    this.alert=true
    this.addResult.reset({})
  }
  closeAlert(){
    this.alert=false
  }

}
