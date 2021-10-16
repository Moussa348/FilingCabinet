import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthRequest } from '../model/auth-request';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  url = "http://localhost:4444/auth"

  constructor(private http: HttpClient) { }

  login(authRequest : AuthRequest){
    return this.http.post(this.url + "/login",authRequest, {responseType: 'text'})
  }
}
