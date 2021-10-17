import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FolderDetail } from '../model/folder-detail';

@Injectable({
  providedIn: 'root'
})
export class FolderService {
  url = "http://localhost:4444/folder"

  constructor(private http: HttpClient) { }

  getListFolderByPatientId(patientId){
    return this.http.get<FolderDetail[]>(this.url + '/getListFolderDetailByPatientId/' + patientId);
  }
}
