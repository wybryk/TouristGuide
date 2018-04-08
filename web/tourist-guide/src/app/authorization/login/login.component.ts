import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthorizationService} from '../authorization.service';
import {UserAccount} from '../../common/model/user-account';
import {StoreService} from '../../common/store.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user: UserAccount;
  errors = false;

  constructor(
    private authService: AuthorizationService,
    private router: Router,
    private store: StoreService
  ) {
  }

  ngOnInit() {
    this.user = new UserAccount();
  }

  onSubmit(): void {
    this.authService.login(this.user).subscribe(result => {
        this.store.currentAccount = result;
        this.router.navigate(['/dashboard']);
      },
      () => {
        this.errors = true;
      }
    );
  }
}
