import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FileService } from 'src/app/service/file.service';
import { UploadStatusComponent } from '../upload-status/upload-status.component';

@Component({
  selector: 'app-upload-file',
  templateUrl: './upload-file.component.html',
  styleUrls: ['./upload-file.component.css'],
})
export class UploadFileComponent implements OnInit {
  @Input() folderId;
  formData: FormData = new FormData();
  isUploading = true;
  description = new FormControl('', [
    Validators.required,
    Validators.minLength(8),
  ]);

  constructor(
    public activeModal: NgbActiveModal,
    private modalService: NgbModal,
    private fileService: FileService
  ) {}

  ngOnInit(): void {
    console.log(this.folderId);
  }

  setFormData() {
    this.formData.append('folderId', this.folderId);
    //this.formData.append("description",this.description.value);
    this.formData.append('description', this.description.value);
  }

  onFileChanged($event) {
    const file = $event.target.files[0];
    console.log(file.name);
    this.formData.append('multipartFile', file);
  }

  upload() {
    this.setFormData();

    this.fileService.upload(this.formData).subscribe(
      (data) => {
        console.log(data);
        this.openUploadStatus("LE FICHIER A ÉTÉ TÉLÉVERSÉ AVEC SUCCÈS!",true);
        this.activeModal.close();
      },
      (err) => {
        this.openUploadStatus("UNE ERREUR À ÉTÉ RENCONTRÉ LORS DU TÉLÉVERSÉ DU FICHIER...",false);
        console.log(err);
      }
    );
  }

  openUploadStatus(status,isGood) {
    const modalRef = this.modalService.open(UploadStatusComponent, {
      centered: true,
      scrollable: true,
    });
    modalRef.componentInstance.status = status;
    modalRef.componentInstance.isGood = isGood;
  }
}
