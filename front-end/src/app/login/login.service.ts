import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Subject } from 'rxjs';
import { User } from "../model/user"
import { DatePipe } from '@angular/common';
import { isNullOrUndefined } from 'util';

interface LoginResponse{
  email: string
  jwt: string
  expirationDate: Date
}

interface IsValidToken{
  valid: boolean
}

@Injectable()
export class LoginService{
  private logInUrl : string
  private validateTokenUrl : string
  user = new Subject<User>()
  constructor(private http: HttpClient){
    this.logInUrl = "http://localhost:8080/uncles/auth/validate"
    this.validateTokenUrl = "http://localhost:8080/uncles/auth/isTokenValid"
  }

  public signIn(email: string, password: string){
   return this.http.post<LoginResponse>(this.logInUrl,
      {
        email: email,
        password: password
      }
    )
  }

  public isValidToken(email: string, jwt: string){
    return this.http.post<IsValidToken>(this.validateTokenUrl,
       {
         email: email,
         jwt: jwt
       }
     )
   }

  public setSession(loginInfo: LoginResponse) {
    localStorage.setItem('jwt_token', loginInfo.jwt);
    localStorage.setItem('user_email', loginInfo.email);
    localStorage.setItem("expires_at", loginInfo.expirationDate.toString());
  }

  public logout() {
    localStorage.removeItem("jwt_token");
    localStorage.removeItem("user_email");
    localStorage.removeItem("expires_at");
  }

  public clearSession() {
    this.logout();
  }

  public isLoggedIn() {
    const exipiryDate = this.getExpiration();
    return !isNullOrUndefined(this.getAuthToken())  &&
            !isNullOrUndefined(exipiryDate) && exipiryDate > new Date;
  }

  public isLoggedOut() {
    return !this.isLoggedIn();
  }

  public getAuthToken() {
    return localStorage.getItem("jwt_token");
  }

  public getEmail() {
    return localStorage.getItem("user_email");
  }

  private getExpiration() {
      const expiration = localStorage.getItem("expires_at");
      if(expiration != undefined){
        return new Date(expiration);
      } else
      return null;
  }

}
