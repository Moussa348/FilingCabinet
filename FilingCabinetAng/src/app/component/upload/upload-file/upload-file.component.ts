import { DatePipe } from '@angular/common';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FileDetail } from 'src/app/model/file-detail';
import { FileService } from 'src/app/service/file.service';
import { UploadStatusComponent } from '../upload-status/upload-status.component';

@Component({
  selector: 'app-upload-file',
  templateUrl: './upload-file.component.html',
  styleUrls: ['./upload-file.component.css'],
})
export class UploadFileComponent implements OnInit {
  @Input() folderId;
  @Input() name;
  @Input() role;

  uploadForm: FormGroup;

  formData: FormData = new FormData();
  fileNameExist = false;
  isUploading = true;
  hasFile = false;
  file;

  @Output() pushFile = new EventEmitter();

  constructor(
    public activeModal: NgbActiveModal,
    private modalService: NgbModal,
    private fileService: FileService
  ) {}

  ngOnInit(): void {
    this.setFormGroup();
    console.log(this.folderId);
  }

  ngDoCheck(): void {
    //Called every time that the input properties of a component or a directive are checked. Use it to extend change detection by performing a custom check.
    //Add 'implements DoCheck' to the class.
    if (this.file && this.hasFile == false) {
      console.log('HAS FILE');
      this.hasFile = true;
    }
  }

  setFormGroup() {
    this.uploadForm = new FormGroup({
      description: new FormControl('', [
        Validators.required,
        Validators.min(8),
      ]),
      fileControl: new FormControl('', Validators.required),
    });
  }

  setFormData() {
    this.formData.append('folderId', this.folderId);
    //this.formData.append("description",this.description.value);
    this.formData.append('description', this.uploadForm.get('description').value);
    this.formData.append('multipartFile', this.file);
  }

  onFileChanged($event) {
    this.file = $event.target.files[0];
    console.log(this.file.name);
  }

  upload() {
    this.setFormData();

    this.fileService.upload(this.formData).subscribe(
      (data) => {
        console.log(data);
        this.openUploadStatus('LE FICHIER A ÉTÉ TÉLÉVERSÉ AVEC SUCCÈS!', true);
        this.createFile(data);
        this.activeModal.close();
      },
      (err) => {
        this.openUploadStatus(
          'UNE ERREUR À ÉTÉ RENCONTRÉ LORS DU TÉLÉVERSÉ DU FICHIER...',
          false
        );
        console.log(err);
      }
    );
  }

  createFile(id) {
    let file: FileDetail = new FileDetail();
    file.id = id;
    file.filename = this.file.name;
    file.description = this.formData.get('description').toString();
    file.uploadBy = this.name;
    file.uploadDate = new Date().toString();
    //file.uploadDate = this.datePipe.transform(new Date(),"yyyy-MM-dd HH:mm:ss")

    this.pushFile.emit(file);
  }

  openUploadStatus(status, isGood) {
    const modalRef = this.modalService.open(UploadStatusComponent, {
      centered: true,
      scrollable: true,
    });
    modalRef.componentInstance.status = status;
    modalRef.componentInstance.isGood = isGood;
  }

  isFileNameExist() {
    return this.fileNameExist;
  }

  getHasFile() {
    return this.hasFile;
  }

  isFieldTouched(name) {
    return this.uploadForm.get(name).touched;
  }

  validateField(name) {
    return this.uploadForm.get(name).valid;
  }
}
