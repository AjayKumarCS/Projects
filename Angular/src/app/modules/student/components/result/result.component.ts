import { Component, OnInit } from '@angular/core';
import { SearchResultService } from '../../services/search-result.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.css']
})
export class ResultComponent implements OnInit {

  constructor(private result:SearchResultService, private router:ActivatedRoute, private redirector:Router) { }
  studentResult:any[] = []
  ngOnInit(): void {
    this.result.searchResult(this.router.snapshot.params['rollno'],this.router.snapshot.params['name']).subscribe((response:any)=>{
      if(response.length==0){
        alert("No Result Found")
        this.redirector.navigate(["/student/home"]);
      } else {
        this.studentResult = response
      }
    })
  }

}
