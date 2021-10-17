import { Component, Input, OnInit } from '@angular/core';
import { saveAs } from 'file-saver';
import { FileService } from 'src/app/service/file.service';

@Component({
  selector: 'app-download-file',
  templateUrl: './download-file.component.html',
  styleUrls: ['./download-file.component.css']
})
export class DownloadFileComponent implements OnInit {
  @Input() id;
  @Input() fileName;
  constructor(
    private fileService : FileService
  ) { }

  ngOnInit(): void {
  }

  download(){
    this.fileService.download(this.id).subscribe(
      (data) =>{
        console.log(data);
        var blob = new Blob([data],{type:data.type});
        saveAs(blob,this.fileName);
      }
    );
  }

}
