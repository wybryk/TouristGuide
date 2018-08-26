import {Component, OnInit} from '@angular/core';
import {AuthorizationService} from '../authorization/authorization.service';
import {UserDetail} from '../common/model/user-detail';
import {StoreService} from '../common/store.service';

@Component({
  selector: 'app-pages',
  templateUrl: './pages.component.html',
  styleUrls: ['./pages.component.css']
})

export class PagesComponent implements OnInit{
  user = new UserDetail();

  constructor(public authService: AuthorizationService,
              private store: StoreService) {
    this.user = this.store.currentAccount;
  }

  ngOnInit() {
    //this.user = this.store.currentAccount;
  }

  logout(): void {
    this.authService.logout();
  }
}
