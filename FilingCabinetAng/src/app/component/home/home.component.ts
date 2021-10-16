import { Component, OnInit } from '@angular/core';
import { getEmail, getRole } from 'src/app/util/jwtUtil';
import {
  animate,
  state,
  style,
  transition,
  trigger,
} from '@angular/animations';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  animations: [
    trigger('fade', [
      state('void', style({ opacity: 0 })),

      transition(':enter, :leave', [animate(1000)]),
    ]),
  ]
})
export class HomeComponent implements OnInit {
  email = getEmail();
  role = getRole();

  constructor() { }

  ngOnInit(): void {
    console.log(this.email + " " + this.role)
  }

}
