import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FileService {
  url = "http://localhost:4444/file"

  constructor(private http: HttpClient) { }

  upload(data){
    return this.http.post(this.url + "/upload",data,{responseType:'text'});
  }

  download(id){
    return this.http.get(this.url + "/download/" + id,{responseType:"blob"});
  }

  existsByFileNameAndFolderId(fileName,folderId){
    const params = new HttpParams().set("fileName",fileName).set("folderId",folderId);
    return this.http.get<boolean>(this.url + "/existsByFileNameAndFolderId/",{params:params});
  }
}
