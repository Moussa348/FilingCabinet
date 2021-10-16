import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PatientFolder } from '../model/patient-folder';

@Injectable({
  providedIn: 'root'
})
export class PatientService {
  url = "http://localhost:4444/patient"

  constructor(private http: HttpClient) { }

  getListPatientFolder(){
    return this.http.get<PatientFolder[]>(this.url + '/getListPatientFolder');
  }

}
