import { Component, Input, OnInit } from '@angular/core';
import { Log } from 'src/app/model/log';
import { PagingRequest } from 'src/app/model/paging-request';
import { SuperUserService } from 'src/app/service/super-user.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-list-log',
  templateUrl: './list-log.component.html',
  styleUrls: ['./list-log.component.css'],
})
export class ListLogComponent implements OnInit {
  @Input() fileId;
  logs: Log[] = new Array();
  pagingRequest: PagingRequest = new PagingRequest();

  constructor(
    public activeModal: NgbActiveModal,
    private superUserService: SuperUserService
  ) {}

  ngOnInit(): void {
    this.getListLogByFileId();
  }

  setPagingRequest(noPage: number, size: number) {
    this.pagingRequest.noPage = noPage;
    this.pagingRequest.size = size;
  }

  getListLogByFileId() {
    this.setPagingRequest(0, 10);

    this.superUserService
      .getListLogByFileId(this.fileId, this.pagingRequest)
      .subscribe(
        (data) => {
          this.logs = data;
          console.log(this.logs);
        },
        (err) => {
          console.log(err);
        }
      );
  }

}
