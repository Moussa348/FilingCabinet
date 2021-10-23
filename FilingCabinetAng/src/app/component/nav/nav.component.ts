import { Component, OnInit } from '@angular/core';
import { getEmail, getRole } from 'src/app/util/jwtUtil';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {
  email = getEmail();
  role = getRole();
  public isMenuCollapsed = true;

  constructor() { }

  ngOnInit(): void {
  }

  isLoggedIn(){
    return this.email != null;
  }

}
