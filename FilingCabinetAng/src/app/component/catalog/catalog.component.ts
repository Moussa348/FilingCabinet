import { Component, OnInit } from '@angular/core';
import {
  animate,
  state,
  style,
  transition,
  trigger,
} from '@angular/animations';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { UploadFileComponent } from '../upload-file/upload-file.component';

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

  constructor(
    private modalService: NgbModal,
  ) {}

  ngOnInit(): void {
  }

  openUpload(){
    const modalRef = this.modalService.open(UploadFileComponent,{
      centered:true,
      scrollable:true
    });

    modalRef.componentInstance.folderId = "61621ca50545544ead443f75";
  }

}
