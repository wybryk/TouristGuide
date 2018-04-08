import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {Observable} from 'rxjs/Observable';
import {UserDetail} from '../common/model/user-detail';
import {UserAccount} from '../common/model/user-account';
import {Router} from '@angular/router';

import 'rxjs/add/operator/map';

@Injectable()
export class AuthorizationService {
  private _userUrl = 'http://localhost:9090/touristguide/rest/user/';

  isUserLoggedIn = false;
  userData: any = {};

  constructor(private http: HttpClient,
              private router: Router) {
  }

  login(user: UserAccount): Observable<any> {
    return this.http.post(this._userUrl + 'login', user).map(
      result => {
        this.isUserLoggedIn = true;
        this.userData = result;
        console.warn('success');
        return result;
      }
    );
  }

  regitration(user: UserDetail): Observable<any> {
    return this.http.post(this._userUrl + 'registration', user).map(
      result => {
        this.isUserLoggedIn = true;
        this.userData = result;
        console.warn('success');
        return result;
      }
    );
  }

  logout(): void {
    this.isUserLoggedIn = false;
    this.router.navigate(['/login']);
  }
}
