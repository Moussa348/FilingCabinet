import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import {
  animate,
  state,
  style,
  transition,
  trigger,
} from '@angular/animations';

@Component({
  selector: 'app-upload-status',
  templateUrl: './upload-status.component.html',
  styleUrls: ['./upload-status.component.css'],
  animations: [
    trigger('fade', [
      state('void', style({ opacity: 0 })),
      transition(':enter, :leave', [animate(1000)]),
    ]),
  ]
})
export class UploadStatusComponent implements OnInit {
  @Input() status;
  @Input() isGood;
  state = 'pending';

  constructor(public activeModal: NgbActiveModal) {}

  ngOnInit(): void {
    console.log(this.isGood);
    console.log(this.status);
    setTimeout(() => {
      this.state = 'done';
    }, 1000);
  }
}
