import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Subject } from 'rxjs';
import { User } from "../model/user"

interface LoginResponse{
  email: string
  jwt: string
}

@Injectable()
export class LoginService{
  private logInUrl : string
  user = new Subject<User>()
  constructor(private http: HttpClient){
    this.logInUrl = "http://localhost:8080/uncles/auth/validate"
  }

  signIn(email: string, password: string){
   return this.http.post<LoginResponse>(this.logInUrl,
      {
        email: email,
        password: password
      }
    )
  }
}