import {Component, OnInit} from '@angular/core';
import {AuthorizationService} from '../authorization.service';
import {Router} from '@angular/router';
import {UserDetail} from '../../common/model/user-detail';
import {UserAccount} from '../../common/model/user-account';
import {StoreService} from '../../common/store.service';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})

export class RegistrationComponent implements OnInit {
  user: UserDetail;
  registrationForm: FormGroup;
  password: string;
  errorMessages: string = '';

  constructor(private authService: AuthorizationService,
              private router: Router,
              private store: StoreService,
              private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.user = new UserDetail();
    this.user.account = new UserAccount();
    this.registrationForm = this.formBuilder.group({
      name: new FormControl('', Validators.required),
      email: new FormControl('', [
        Validators.pattern("[^ @]*@[^ @]*"),
        Validators.required
      ]),
      login: new FormControl('', [
        Validators.minLength(5),
        Validators.required
      ]),
      password: new FormControl('', [
        Validators.minLength(5),
        Validators.required
      ]),
      repeatPassword: new FormControl('', [
        Validators.minLength(5),
        Validators.required
      ]),
    });
  }

  onSubmit(): void {
    this.errorMessages = '';
    if(this.user.account.password !== this.password) {
      this.errorMessages += 'Wpisane hasła nie są takie same.';
    }

    if(this.errorMessages.length === 0) {
      this.authService.registration(this.user).subscribe(result => {
          this.store.currentAccount = result;
          this.router.navigate(['/dashboard']);
        },
        error => {
          this.errorMessages += error.error;
        }
      );
    }
  }
}
