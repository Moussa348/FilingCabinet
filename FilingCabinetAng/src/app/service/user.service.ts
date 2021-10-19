import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { PagingRequest } from '../model/paging-request';
import { FileDetail } from '../model/file-detail';
import { CategoryDetail } from '../model/category-detail';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  url = "http://localhost:4444/user"

  constructor(private http: HttpClient) { }

  getListFileDetailUserView(pagingRequest:PagingRequest){
    const params = new HttpParams()
    .set('noPage',pagingRequest.noPage.toString())
    .set('size',pagingRequest.size.toString())
    
    return this.http.get<FileDetail[]>(this.url + "/getListFileDetailUserView/" + pagingRequest.folderId, {params:params})
  }

  getListCategoryDetailUserView(){
    return this.http.get<CategoryDetail[]>(this.url + "/getListCategoryDetailUserView");
  }
  
}
