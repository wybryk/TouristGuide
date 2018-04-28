import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthorizationService} from '../authorization.service';
import {UserAccount} from '../../common/model/user-account';
import {StoreService} from '../../common/store.service';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user: UserAccount;
  loginForm: FormGroup;
  errorMessages: string = '';

  constructor(private authService: AuthorizationService,
              private router: Router,
              private store: StoreService,
              private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.user = new UserAccount();
    this.loginForm = this.formBuilder.group({
      login: new FormControl('', [
        Validators.minLength(5),
        Validators.required
      ]),
      password: new FormControl('', [
        Validators.minLength(5),
        Validators.required
      ]),
    });
  }

  onSubmit(): void {
    this.errorMessages = '';
    this.authService.login(this.user).subscribe(result => {
        this.store.currentAccount = result;
        this.router.navigate(['/dashboard']);
      },
      error => {
        this.errorMessages += error.error;
      }
    );
  }
}
