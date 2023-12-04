import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class ResultManagerService {

  constructor(private http:HttpClient) { }

  url="http://localhost:3000/result"
  getResult(){
    return this.http.get(this.url);
  }
  saveResult(data:any){
    return this.http.post(this.url,data)
  }
  deleteResult(id:any){
    return this.http.delete(`${this.url}/${id}`)
  }
  getCurrentResult(id:any){
    return this.http.get(`${this.url}/${id}`)
  }
  editCurrentResult(id:any,data:any){
    return this.http.put(`${this.url}/${id}`,data)
  }
}
