import { Component, EventEmitter, OnInit, Output } from '@angular/core';
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
    children: [
      { name: 'EVALUATION' },
      { name: 'PRISE DE SANG' },
      { name: 'CHIRURGIE' },
    ],
  },
  {
    name: 'Mamady Blaise',
    children: [
      { name: 'EVALUATION' },
      { name: 'PRISE DE SANG' },
      { name: 'CHIRURGIE' },
    ],
  },
];

/** Flat node with expandable and level information */
interface FlatNode {
  id: string;
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
  currentPatientId;
  @Output() folderId = new EventEmitter();

  TREE_DATA2: PatientFolder[];

  private _transformer = (node: any, level: number) => {
    this.currentPatientId = node.patientName ? node.patientId : '';
    return {
      id: node.patientName ? node.patientId : '',
      expandable: true,
      name: node.patientName ? node.patientName : node,
      level: level,
    };
  };

  listPatientFolder: PatientFolder[] = new Array();

  treeControl;

  treeFlattener;

  dataSource;

  constructor(private patientService: PatientService) {
    //this.dataSource.data = TREE_DATA;
  }

  hasChild = (_: number, node: FlatNode) => node.expandable;

  ngOnInit(): void {
    this.getListPatientFolder(0, 10);
  }

  setPagingRequest(noPage: number, size: number) {
    this.pagingRequest.noPage = noPage;
    this.pagingRequest.size = size;
  }

  getListPatientFolder(noPage: number, size: number) {
    this.setPagingRequest(noPage, size);
    this.patientService.getListPatientFolder(this.pagingRequest).subscribe(
      (data) => {
        this.listPatientFolder.push.apply(this.listPatientFolder, data);

        this.treeControl = new FlatTreeControl<FlatNode>(
          (node) => node.level,
          (node) => node.expandable
        );

        this.treeFlattener = new MatTreeFlattener(
          this._transformer,
          (node) => node.level,
          (node) => node.expandable,
          (node) => (node.mapFolders ? Object.keys(node.mapFolders) : null)
        );
        this.dataSource = new MatTreeFlatDataSource(
          this.treeControl,
          this.treeFlattener
        );

        this.dataSource.data = this.listPatientFolder;
      },
      (err) => {
        console.log(err);
      }
    );
  }

  nodeEvent($event) {
    if ($event.id != '') {
      this.currentPatientId = $event.id;
      //console.log(this.currentPatientId);
    }
    const patientFolder = this.listPatientFolder.filter(
      (patientFolder) => patientFolder.patientId == this.currentPatientId
    );
    const folderId = patientFolder[0].mapFolders[$event.name];

    if (folderId != undefined) {
      this.folderId.emit(folderId);
    }
  }
}
