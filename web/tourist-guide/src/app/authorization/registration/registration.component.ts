import { Component, OnInit } from '@angular/core';
import {AuthorizationService} from '../authorization.service';
import {Router} from '@angular/router';
import {UserDetail} from '../../common/model/user-detail';
import {UserAccount} from '../../common/model/user-account';
import {StoreService} from '../../common/store.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})

export class RegistrationComponent implements OnInit {
  user: UserDetail;
  password: string;
  errors = false;

  constructor(
    private authService: AuthorizationService,
    private router: Router,
    private store: StoreService
  ) { }

  ngOnInit() {
    this.user = new UserDetail();
    this.user.account = new UserAccount();
  }

  onSubmit(): void {
    this.authService.regitration(this.user).subscribe(result => {
        this.store.currentAccount = result;
        this.router.navigate(['/dashboard']);
      },
      () => {
        this.errors = true;
      }
    );
  }
}
