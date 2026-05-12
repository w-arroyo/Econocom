import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SuccessfulLoginRequest } from '../models/basic_request_response.model';
import { LoginRequest } from '../models/login_request.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private httpClient: HttpClient;
  private URL: string= 'http://localhost:8080/api/users';

  constructor(httpClient: HttpClient){
    this.httpClient=httpClient;
  }

  login(loginRequest:LoginRequest): Observable<SuccessfulLoginRequest>{
    return this.httpClient.post<SuccessfulLoginRequest>(`${this.URL}/login`,loginRequest);
  }

  loginWithSso(email:string): Observable<SuccessfulLoginRequest>{
    return this.httpClient.get<SuccessfulLoginRequest>(`${this.URL}/sso?email=${email}`);
  }

}
