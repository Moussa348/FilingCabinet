import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PagingRequest } from '../model/paging-request';
import { PatientFolder } from '../model/patient-folder';

@Injectable({
  providedIn: 'root'
})
export class PatientService {
  url = "http://localhost:4444/patient"

  constructor(private http: HttpClient) { }

  getListPatientFolder(pagingRequest : PagingRequest){
    const params = new HttpParams()
    .set('noPage',pagingRequest.noPage.toString())
    .set('size',pagingRequest.size.toString())
    return this.http.get<PatientFolder[]>(this.url + '/getListPatientFolder',{params:params});
  }

}
