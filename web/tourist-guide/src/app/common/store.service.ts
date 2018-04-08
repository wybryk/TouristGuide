import { Injectable } from '@angular/core';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';

@Injectable()
export class StoreService {

  private _currentAccount: BehaviorSubject<any> = new BehaviorSubject<any>({});

  constructor() {
    // localStorage.clear();
    if (localStorage.getItem('currentAccount')) {
      this.currentAccount = JSON.parse(localStorage.getItem('currentAccount'));
    }
  }

  get currentAccount() {
    return this._currentAccount.getValue();
  }

  set currentAccount(val: any) {
    this._currentAccount.next(val);
    localStorage.setItem('currentAccount', JSON.stringify(val));
    //console.warn(JSON.parse(localStorage.getItem('currentAccount')));
  }
}
