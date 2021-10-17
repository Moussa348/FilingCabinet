import { Component, OnInit } from '@angular/core';
import { PatientFolder } from 'src/app/model/patient-folder';
import { PatientService } from 'src/app/service/patient.service';
import {NestedTreeControl} from '@angular/cdk/tree';
import {MatTreeNestedDataSource} from '@angular/material/tree';

/**
 * Food data with nested structure.
 * Each node has a name and an optional list of children.
 */
 interface FoodNode {
  name: string;
  children?: FoodNode[];
}

const TREE_DATA: FoodNode[] = [
  {
    name: 'Fruit',
    children: [
      {name: 'Apple'},
      {name: 'Banana'},
      {name: 'Fruit loops'},
    ]
  }
];
@Component({
  selector: 'app-list-patient-folder',
  templateUrl: './list-patient-folder.component.html',
  styleUrls: ['./list-patient-folder.component.css']
})
export class ListPatientFolderComponent implements OnInit {
  treeControl = new NestedTreeControl<FoodNode>(node => node.children);
  dataSource = new MatTreeNestedDataSource<FoodNode>();

  listPatientFolder: PatientFolder[] = new Array();

  constructor(private patientService: PatientService) {
    this.dataSource.data = TREE_DATA;
  }

  ngOnInit(): void {
    this.getListPatientFolder();
  }

  hasChild = (_: number, node: FoodNode) => !!node.children && node.children.length > 0;

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
