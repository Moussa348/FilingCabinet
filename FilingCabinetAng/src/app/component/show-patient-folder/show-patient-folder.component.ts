import { Component, Input, OnInit } from '@angular/core';
import { NgbAccordionConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FileDetail } from 'src/app/model/file-detail';
import { PagingRequest } from 'src/app/model/paging-request';
import { FileService } from 'src/app/service/file.service';
import { SuperUserService } from 'src/app/service/super-user.service';
import { UserService } from 'src/app/service/user.service';
import { ROLES } from 'src/app/util/constant';
import {
  animate,
  state,
  style,
  transition,
  trigger,
} from '@angular/animations';
import { UploadFileComponent } from '../upload/upload-file/upload-file.component';

@Component({
  selector: 'app-show-patient-folder',
  templateUrl: './show-patient-folder.component.html',
  styleUrls: ['./show-patient-folder.component.css'],
  animations: [
    trigger('fade', [
      state('void', style({ opacity: 0 })),
      transition(':enter, :leave', [animate(1000)]),
    ]),
  ],
  providers: [NgbAccordionConfig],
})
export class ShowPatientFolderComponent implements OnInit {
  @Input() name;
  @Input() folderId;
  @Input() role;
  oldFolderId;
  isUndefined = true;
  files: FileDetail[] = new Array();
  pagingRequest: PagingRequest = new PagingRequest();

  constructor(
    private userService: UserService,
    private superUserService: SuperUserService,
    config: NgbAccordionConfig,
    private modalService: NgbModal
  ) {
    config.closeOthers = true;
    config.type = 'dark';
  }

  ngOnInit(): void {
    console.log(this.folderId);
    console.log(this.role);
  }

  ngDoCheck(): void {
    if (
      (this.folderId != undefined && this.isUndefined) ||
      this.oldFolderId != this.folderId
    ) {
      this.setup();
      return;
    }
  }

  setup() {
    this.files = new Array();
    console.log(this.folderId);
    if (this.role == ROLES[0]) {
      this.getListFileForUser(0, 10);
    }

    if (this.role == ROLES[1]) {
      this.getListFileForSuperUser(0, 10);
    }
    this.isUndefined = false;
    this.oldFolderId = this.folderId;
  }

  openUpload() {
    const modalRef = this.modalService.open(UploadFileComponent, {
      centered: true,
      scrollable: true,
    });

    modalRef.componentInstance.folderId = this.folderId;
    modalRef.componentInstance.name = this.name;
    modalRef.componentInstance.role = this.role;

    modalRef.componentInstance.pushFile.subscribe((file) => {
      console.log(file);
      setTimeout(() => {
        this.files.unshift(file);
      }, 1000);
    });
  }

  setPagingRequest(noPage: number, size: number) {
    this.pagingRequest.noPage = noPage;
    this.pagingRequest.folderId = this.folderId;
    this.pagingRequest.size = size;
  }

  getListFileForSuperUser(noPage: number, size: number) {
    this.setPagingRequest(noPage, size);

    this.superUserService
      .getListFileDetailSuperUserView(this.pagingRequest)
      .subscribe(
        (data) => {
          this.files.push.apply(this.files, data);
          console.log(data);
        },
        (err) => {
          console.log(err);
        }
      );
  }

  getListFileForUser(noPage: number, size: number) {
    this.setPagingRequest(noPage, size);

    this.userService.getListFileDetailUserView(this.pagingRequest).subscribe(
      (data) => {
        this.files.push.apply(this.files, data);
        console.log(data);
      },
      (err) => {
        console.log(err);
      }
    );
  }
}
