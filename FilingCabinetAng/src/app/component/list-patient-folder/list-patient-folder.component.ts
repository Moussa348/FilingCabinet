import { Component, OnInit } from '@angular/core';
import { PatientFolder } from 'src/app/model/patient-folder';
import { PatientService } from 'src/app/service/patient.service';
import { FlatTreeControl, NestedTreeControl } from '@angular/cdk/tree';
import {
  MatTreeFlatDataSource,
  MatTreeFlattener,
  MatTreeNestedDataSource,
} from '@angular/material/tree';
import { PagingRequest } from 'src/app/model/paging-request';

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
  pagingRequest: PagingRequest = new PagingRequest();

  TREE_DATA2: PatientFolder[];
  /*
  private _transformer = (node: FoodNode, level: number) => {
    return {
      expandable: !!node.children && node.children.length > 0,
      name: node.name,
      level: level,
    };
  };
  */

  private _transformer = (node: any, level: number) => {
    console.log(node);
    return {
      expandable: true,
      name: node.patientName ? node.patientName : node,
      level: level
    };
  };

  listPatientFolder: PatientFolder[] = new Array();

  treeControl;

  treeFlattener;

  dataSource ;

  constructor(private patientService: PatientService) {
    //this.dataSource.data = TREE_DATA;
  }

  hasChild = (_: number, node: ExampleFlatNode) => node.expandable;

  ngOnInit(): void {
    this.getListPatientFolder(0,10);
  }

  setPagingRequest(noPage: number, size: number) {
    this.pagingRequest.noPage = noPage;
    this.pagingRequest.size = size;
  }

  getListPatientFolder(noPage: number, size: number) {
    this.setPagingRequest(noPage, size);
    this.patientService.getListPatientFolder(this.pagingRequest).subscribe(
      (data) => {
        this.listPatientFolder.push.apply(this.listPatientFolder,data);
        console.log(this.listPatientFolder);
        
        this.treeControl = new FlatTreeControl<ExampleFlatNode>(
          (node) => node.level,
          (node) => node.expandable
        );

        this.treeFlattener = new MatTreeFlattener(
          this._transformer,
          (node) => node.level,
          (node) => node.expandable,
          (node) => node.mapFolders ? Object.keys(node.mapFolders):null
        );
        this.dataSource = new MatTreeFlatDataSource(this.treeControl, this.treeFlattener);

        this.dataSource.data = this.listPatientFolder;
        console.log(Object.keys(this.listPatientFolder[0].mapFolders));
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
