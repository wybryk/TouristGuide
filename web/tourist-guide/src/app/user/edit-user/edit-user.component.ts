import { Component, OnInit } from '@angular/core';
import {UserDetail} from '../../common/model/user-detail';
import {StoreService} from '../../common/store.service';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {
  user: UserDetail;
  password: string;
  errors: boolean = false;

  constructor(private store: StoreService) { }

  ngOnInit() {
    this.user = this.store.currentAccount;
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
    console.warn("editUserAccount");
  }

  deleteAccount() {
    console.warn("deleteAccount");
  }
}
