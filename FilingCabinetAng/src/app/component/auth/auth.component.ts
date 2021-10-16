import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthGuardService } from 'src/app/service/auth-guard.service';
import {
  animate,
  state,
  style,
  transition,
  trigger,
} from '@angular/animations';
import { AuthService } from 'src/app/service/auth.service';
import { AuthRequest } from 'src/app/model/auth-request';
import { getEmail } from 'src/app/util/jwtUtil';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css'],
  animations: [
    trigger('fade', [
      state('void', style({ opacity: 0 })),

      transition(':enter, :leave', [animate(1000)]),
    ]),
  ]
})
export class AuthComponent implements OnInit {

  authFormGroup: FormGroup;
  staticAlert: any;

  constructor(
    private authService: AuthService,
    private router: Router,
    private authGuardService:AuthGuardService
  ) { }

  ngOnInit(): void {
    this.setAuthFormGroup();
  }

  setAuthFormGroup() {
    this.authFormGroup = new FormGroup({
      email: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
    });
  }

  onSubmit() {
    const authRequest = new AuthRequest();

    authRequest.email = this.authFormGroup.get('email').value;
    authRequest.password = this.authFormGroup.get('password').value;

    if (this.authFormGroup.valid) {
      this.authService
        .login(authRequest)
        .subscribe(
          (data) => {
            if (data != null) {
              this.authGuardService.login(data);
              this.router.navigate(['/home']);
            } 
          },
          (err) => {
            console.log(err);
          }
        );
    }
  }

  validatePassword(){
    return this.authFormGroup.get('password').value.length >=8;
  }

  validateField(formControlName){
    return this.authFormGroup.get(formControlName).valid;
  }

  isFieldTouched(formControlName){
    return this.authFormGroup.get(formControlName).touched;
  }

  isLoggedIn() {
    return this.authGuardService.isLoggedIn();
  }

  logout() {
    this.authGuardService.logout();
  }

  isFormValid(){
    return this.authFormGroup.valid && this.validatePassword();
  }


}
