import { Component, OnInit } from '@angular/core';
import { PatientFolder } from 'src/app/model/patient-folder';
import { PatientService } from 'src/app/service/patient.service';

@Component({
  selector: 'app-list-patient-folder',
  templateUrl: './list-patient-folder.component.html',
  styleUrls: ['./list-patient-folder.component.css']
})
export class ListPatientFolderComponent implements OnInit {

  listPatientFolder: PatientFolder[] = new Array();

  constructor(private patientService: PatientService) {}

  ngOnInit(): void {
    this.getListPatientFolder();
  }

  getListPatientFolder() {
    this.patientService.getListPatientFolder().subscribe(
      (data) => {
        this.listPatientFolder = data;
        console.log(this.listPatientFolder);
      },
      (err) => {
        console.log(err);
      }
    );
  }
}
