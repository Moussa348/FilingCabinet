import { Component, Input, OnInit } from '@angular/core';
import { FileDetail } from 'src/app/model/file-detail';
import {
  animate,
  state,
  style,
  transition,
  trigger,
} from '@angular/animations';
import { getRole } from 'src/app/util/jwtUtil';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ListLogComponent } from '../list-log/list-log.component';

@Component({
  selector: 'app-show-file-detail',
  templateUrl: './show-file-detail.component.html',
  styleUrls: ['./show-file-detail.component.css'],
  animations: [
    trigger('fade', [
      state('void', style({ opacity: 0 })),

      transition(':enter, :leave', [animate(1000)]),
    ]),
  ]
})
export class ShowFileDetailComponent implements OnInit {
  role = getRole();
  @Input() file:FileDetail = new FileDetail();

  constructor( private modalService: NgbModal) { }

  ngOnInit(): void {
    console.log(this.file);
  }

  openLogs(){
    const modalRef = this.modalService.open(ListLogComponent, {
      centered: true,
      scrollable: true,
    });

    modalRef.componentInstance.fileId = this.file.id;
  }

}
