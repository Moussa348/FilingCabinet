import { Component, Input, OnInit } from '@angular/core';
import { FileDetail } from 'src/app/model/file-detail';

@Component({
  selector: 'app-show-file-detail',
  templateUrl: './show-file-detail.component.html',
  styleUrls: ['./show-file-detail.component.css']
})
export class ShowFileDetailComponent implements OnInit {

  @Input() fileDetail:FileDetail = new FileDetail();

  constructor() { }

  ngOnInit(): void {
    console.log(this.fileDetail);
  }

}
