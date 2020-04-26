import { Component, OnInit } from '@angular/core';
import { User } from '../model/user';

import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../service/user-service.service';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {

  user : User;

  constructor(private route: ActivatedRoute, private router: Router, private userService: UserService) { 
        this.user = new User();
      }

  onSubmit() {
    this.userService.save(this.user).subscribe(result => this.gotoUserList());
  }
 
  gotoUserList() {
    this.router.navigate(['/users']);
  }
  ngOnInit(): void {
  }

}
