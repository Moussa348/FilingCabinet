import { Component, Input, OnInit } from '@angular/core';
import { FileDetail } from 'src/app/model/file-detail';
import { PagingRequest } from 'src/app/model/paging-request';
import { FileService } from 'src/app/service/file.service';
import { SuperUserService } from 'src/app/service/super-user.service';
import { UserService } from 'src/app/service/user.service';
import { ROLES } from 'src/app/util/constant';

@Component({
  selector: 'app-show-patient-folder',
  templateUrl: './show-patient-folder.component.html',
  styleUrls: ['./show-patient-folder.component.css']
})
export class ShowPatientFolderComponent implements OnInit {

  @Input() name;
  @Input() folderId;
  @Input() role;
  files: FileDetail[] = new Array();
  pagingRequest: PagingRequest = new PagingRequest();

  constructor(
    private userService: UserService,
    private superUserService: SuperUserService
  ) {}

  ngOnInit(): void {
    console.log(this.folderId);
    console.log(this.role);

    if(this.role == ROLES[0]){
      this.getListFileForUser(0,10);
    }

    if(this.role == ROLES[1]){
      this.getListFileForSuperUser(0,10);
    }
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
          this.files.push.apply(this.files,data);
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
        this.files.push.apply(this.files,data);
        console.log(data);
      },
      (err) => {
        console.log(err);
      }
    );
  }
}
