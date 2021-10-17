import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FileService {
  url = "http://localhost:4444/file"

  constructor(private http: HttpClient) { }

  upload(data){
    return this.http.post(this.url + "/upload",data);
  }

  download(id){
    return this.http.get(this.url + "/download/" + id,{responseType:"blob"});
  }
}
