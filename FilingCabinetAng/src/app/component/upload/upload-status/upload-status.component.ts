import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-upload-status',
  templateUrl: './upload-status.component.html',
  styleUrls: ['./upload-status.component.css'],
})
export class UploadStatusComponent implements OnInit {
  @Input() status;
  @Input() isGood;
  state = 'pending';

  constructor(private activeModal: NgbActiveModal) {}

  ngOnInit(): void {
    console.log(this.isGood);
    console.log(this.status);
    setTimeout(() => {
      this.state = 'done';
      setTimeout(() => {
        this.activeModal.close();
      }, 1500);
    }, 2000);
  }
}
