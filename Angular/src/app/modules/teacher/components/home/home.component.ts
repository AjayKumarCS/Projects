import { Component, OnInit } from '@angular/core';
import { ResultManagerService } from '../../services/result-manager.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private result:ResultManagerService) { }

  results:any[] = [];

  ngOnInit(): void {
    this.result.getResult().subscribe((resultFromServer:any)=>{
      console.log(resultFromServer)
      this.results=resultFromServer;
    })
  }

  delete(item:any){
    this.results.splice(item,1)
    this.result.deleteResult(item).subscribe((response)=>{
      console.log(response)
    })
  }

}
