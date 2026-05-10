import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../../services/login.service';
import { LoginRequest } from '../../models/login_request.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  private loginService: LoginService;
  private router: Router;
  private loginRequestModel: LoginRequest;
  email: string;
  password: string;
  response: string;

  constructor(loginService: LoginService, router: Router){
    this.loginService=loginService;
    this.router=router;
    this.loginRequestModel=new LoginRequest();
    this.response='';
    this.email='';
    this.password='';
  }

  loginRequest(): void{

  }

  loginWithDecathlon(){

  }



}
