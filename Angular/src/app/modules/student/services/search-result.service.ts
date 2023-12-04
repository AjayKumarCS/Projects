import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class SearchResultService {

  constructor(private http:HttpClient) { }

  url="http://localhost:3000/result"
  searchResult(rollno:any,name:any){
    return this.http.get(`${this.url}?rollno=${rollno}&name=${name}`)
  }
}
