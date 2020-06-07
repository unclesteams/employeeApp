import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { LoginService } from './login.service';
import { ReplaySubject } from 'rxjs';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public isLoading = false
  public error: string = null

  constructor(private loginService : LoginService, private router: Router) { }

  ngOnInit(): void {
  }
  loginUser(form: NgForm){
    this.isLoading = true
    const email = form.value.email
    const password = form.value.password
    this.loginService.signIn(email,password).subscribe(
    response =>{
      this.error = null
      this.isLoading = false
      this.loginService.setSession(response)
      this.router.navigate(['/dashboard'])
    },
    error => {
      console.log(error)
      this.error = error.error
      this.isLoading = false
    })
    console.log(form.value)
  }
}
