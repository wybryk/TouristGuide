import { Component, OnInit } from '@angular/core';
import {UserDetail} from '../../common/model/user-detail';
import {StoreService} from '../../common/store.service';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthorizationService} from '../../authorization/authorization.service';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {
  user: UserDetail;
  editUserForm: FormGroup;
  repeatPassword: string;
  errorMessages: string = '';

  constructor(private store: StoreService,
              private authService: AuthorizationService,
              private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.user = this.store.currentAccount;
    this.editUserForm = this.formBuilder.group({
      name: new FormControl('', Validators.required),
      email: new FormControl('', [
        Validators.pattern("[^ @]*@[^ @]*"),
        Validators.required
      ]),
      login: new FormControl('', [
        Validators.minLength(5),
        Validators.required
      ]),
      currentPassword: new FormControl('', [
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

  readImage(event: any) {
    if (event.target.files && event.target.files[0]) {
      var reader = new FileReader();

      reader.onload = (event: any) => {
        this.user.avatar = event.target.result;
      }

      reader.readAsDataURL(event.target.files[0]);
    }
  }

  editUserAccount() {
    this.errorMessages = '';

    if(this.user.account.password !== this.repeatPassword) {
      this.errorMessages += 'Wpisane hasła nie są takie same.\n';
    }

    if(this.errorMessages.length === 0) {
      this.authService.updateAccount(this.user).subscribe(result => {
          this.store.currentAccount = result;
        },
        error => {
          this.errorMessages += error.error + '\n';
        }
      );
    }
  }

  deleteAccount() {
    this.authService.deleteUser(this.user.userDetailId).subscribe(() => {});
  }
}
