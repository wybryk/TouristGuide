import {Component, OnInit} from '@angular/core';
import {UserDetail} from '../../common/model/user-detail';
import {Router} from '@angular/router';
import {Location} from '@angular/common';
import {StoreService} from '../../common/store.service';

@Component({
  selector: 'app-detail-user',
  templateUrl: './detail-user.component.html',
  styleUrls: ['./detail-user.component.css']
})

export class DetailUserComponent implements OnInit {
  user = new UserDetail();

  constructor(private router: Router,
              private location: Location,
              private store: StoreService) {
  }

  ngOnInit() {
    this.user = this.store.currentAccount;
  }

  gotoUserEdit() {
    this.router.navigate(['/user/edit']);
  }

  goBack() {
    this.location.back();
  }

}
