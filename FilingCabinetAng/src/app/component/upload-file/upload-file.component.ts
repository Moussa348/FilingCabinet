import { Component, Input, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { FileService } from 'src/app/service/file.service';

@Component({
  selector: 'app-upload-file',
  templateUrl: './upload-file.component.html',
  styleUrls: ['./upload-file.component.css']
})
export class UploadFileComponent implements OnInit {
  @Input() folderId;
  formData : FormData = new FormData();
  description = new FormControl("",[Validators.required,Validators.minLength(8)]);
  
  constructor(
    public activeModal: NgbActiveModal,
    private fileService:FileService
  ) { }

  ngOnInit(): void {
    console.log(this.folderId)
  }

  setFormData(){
    this.formData.append("folderId",this.folderId);
    //this.formData.append("description",this.description.value);
    this.formData.append("description","asdasdasdasdadasd");
  }
  
  onFileChanged($event){
    this.formData.append("multipartFile",$event.target.files[0]);
  }

  upload(){
    this.setFormData();

    this.fileService.upload(this.formData).subscribe(
      (data) => {
        console.log(data);
        this.activeModal.close();
      },(err) => {
        console.log(err);
      }
    )
  }
  
}
