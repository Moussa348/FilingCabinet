import { Component, OnInit } from '@angular/core';
import { PatientFolder } from 'src/app/model/patient-folder';
import { PatientService } from 'src/app/service/patient.service';
import { FlatTreeControl, NestedTreeControl } from '@angular/cdk/tree';
import {
  MatTreeFlatDataSource,
  MatTreeFlattener,
  MatTreeNestedDataSource,
} from '@angular/material/tree';

/**
 * Food data with nested structure.
 * Each node has a name and an optional list of children.
 */
interface FoodNode {
  name: string;
  children?: FoodNode[];
}

interface FoodNode {
  name: string;
  children?: FoodNode[];
}

const TREE_DATA: FoodNode[] = [
  {
    name: 'Fofana Conde',
    children: [{ name: 'EVALUATION' }, { name: 'PRISE DE SANG' }, { name: 'CHIRURGIE' }],
  },
  {
    name: 'Mamady Blaise',
    children: [{ name: 'EVALUATION' }, { name: 'PRISE DE SANG' }, { name: 'CHIRURGIE' }],
  },
];

/** Flat node with expandable and level information */
interface ExampleFlatNode {
  expandable: boolean;
  name: string;
  level: number;
}
@Component({
  selector: 'app-list-patient-folder',
  templateUrl: './list-patient-folder.component.html',
  styleUrls: ['./list-patient-folder.component.css'],
})
export class ListPatientFolderComponent implements OnInit {
  
  private _transformer = (node: FoodNode, level: number) => {
    return {
      expandable: !!node.children && node.children.length > 0,
      name: node.name,
      level: level,
    };
  };

  listPatientFolder: PatientFolder[] = new Array();

  treeControl = new FlatTreeControl<ExampleFlatNode>(
    (node) => node.level,
    (node) => node.expandable
  );

  treeFlattener = new MatTreeFlattener(
    this._transformer,
    (node) => node.level,
    (node) => node.expandable,
    (node) => node.children
  );

  dataSource = new MatTreeFlatDataSource(this.treeControl, this.treeFlattener);

  constructor(private patientService: PatientService) {
    this.dataSource.data = TREE_DATA;
  }

  hasChild = (_: number, node: ExampleFlatNode) => node.expandable;

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

  nodeEvent($event){
    console.log($event)
  }
}
