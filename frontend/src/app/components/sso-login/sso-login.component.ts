import { Component } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import { EmailFormatValidator } from 'src/app/utils/validators/email.validator';

@Component({
  selector: 'app-sso-login',
  templateUrl: './sso-login.component.html',
  styleUrls: ['./sso-login.component.scss']
})
export class SsoLoginComponent {

  private loginService:LoginService;
  email: string;
  response:string;

  constructor(loginService:LoginService){
    this.loginService=loginService;
    this.email='';
    this.response='';
  }

  loginWithSSO(): void{
    if(this.checkEmail() && EmailFormatValidator.checkEmailFormat(this.email)){
      this.loginService.loginWithSso(this.email)
      .subscribe({
        next: (data)=>{
          console.log(data.token);
          this.response='Login completado.';
        },
        error: (error)=>{
          console.log(error);
          let errorMessage:string=error.error?.message;
          this.response=errorMessage;
        }
      });
    }
    else{
      this.response='Introduzca un formato de email correcto.';
    }
  }

  checkEmail(): boolean{
    if(this.email.trim()===''){
      this.response='El email es requerido';
      return false;
    }
    this.response='';
    return true;
  }

}
