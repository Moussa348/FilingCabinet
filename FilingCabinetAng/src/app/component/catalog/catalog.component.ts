import { Component, OnInit } from '@angular/core';
import {
  animate,
  state,
  style,
  transition,
  trigger,
} from '@angular/animations';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { UploadFileComponent } from '../upload/upload-file/upload-file.component';
import { getEmail, getRole } from 'src/app/util/jwtUtil';

@Component({
  selector: 'app-catalog',
  templateUrl: './catalog.component.html',
  styleUrls: ['./catalog.component.css'],
  animations: [
    trigger('fade', [
      state('void', style({ opacity: 0 })),

      transition(':enter, :leave', [animate(1000)]),
    ]),
  ],
})
export class CatalogComponent implements OnInit {

  folderId;
  role = getRole();
  email = getEmail();
  constructor(
    private modalService: NgbModal,
  ) {}

  ngOnInit(): void {
  }

  outputFolderId($event){
    this.folderId = $event;
    console.log($event);
  }

}
