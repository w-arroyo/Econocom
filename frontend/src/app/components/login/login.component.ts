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
  response: String;
  hidePassword: boolean;

  constructor(loginService: LoginService, router: Router){
    this.loginService=loginService;
    this.router=router;
    this.loginRequestModel=new LoginRequest();
    this.response='';
    this.email='';
    this.password='';
    this.hidePassword=true;
  }

  loginRequest(): void{
    if(this.checkFields()){
      this.login();
    }
  }

  private login(): void{
    this.loginRequestModel.setEmail(this.email);
    this.loginRequestModel.setPassword(this.password);
    this.loginService.login(this.loginRequestModel)
    .subscribe({
      next: (data)=>{
        console.log(data.token);
        this.response='Login completado.';
      },
      error: (error) =>{
        console.log(error);
        let errorMessage:String=error.error?.message;
        this.response=errorMessage;
      }
    });
  }

  loginWithDecathlon(){

  }

  changePasswordVisibilty(): void{
    this.hidePassword = !this.hidePassword;
  }

  private checkFields(): boolean{
    if(this.email.trim()==='' || this.password.trim()===''){
      this.response='Los campos son obligatorios.'
      return false;
    }
    if(!this.isEmailValid()){
      this.response='Introduzca un formato de email correcto.';
      return false;
    }
    if(!this.isPasswordValid()){
      this.response='La contraseña debe tener al menos 4 caracteres.';
      return false;
    }
    return true;
  }

  private isEmailValid(): boolean{
    const emailRegex= /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return emailRegex.test(this.email);
  }

  private isPasswordValid(): boolean{
    return this.password.length>3;
  }

}
