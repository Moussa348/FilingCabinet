import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { PagingRequest } from '../model/paging-request';
import { FileDetail } from '../model/file-detail';
import { CategoryDetail } from '../model/category-detail';
import { Log } from '../model/log';

@Injectable({
  providedIn: 'root'
})
export class SuperUserService {
  
  url = "http://localhost:4444/superUser"

  constructor(private http: HttpClient) { }

  getListFileDetailSuperUserView(pagingRequest:PagingRequest){
    const params = new HttpParams()
    .set('noPage',pagingRequest.noPage.toString())
    .set('size',pagingRequest.size.toString())
    
    return this.http.get<FileDetail[]>(this.url + "/getListFileDetailSuperUserView/" + pagingRequest.folderId, {params:params})
  }

  getListCategoryDetailSuperUserView(){
    return this.http.get<CategoryDetail[]>(this.url + "/getListCategoryDetailUserView");
  }

  getListLogByFileId(fileId, pagingRequest:PagingRequest){
    const params = new HttpParams()
    .set('noPage',pagingRequest.noPage.toString())
    .set('size',pagingRequest.size.toString())
    return this.http.get<Log[]>(this.url + "/getListLogByFileId/" + fileId, {params:params});
  }

}
