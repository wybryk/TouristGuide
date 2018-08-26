import {Component, OnInit} from '@angular/core';
import {AuthorizationService} from '../../authorization/authorization.service';
import {Route} from '../../common/model/route';
import {Router} from '@angular/router';
import {RouteService} from '../route.service';
import {StoreService} from '../../common/store.service';
import {UserAccount} from '../../common/model/user-account';

@Component({
  selector: 'app-routes',
  templateUrl: './routes.component.html',
  styleUrls: ['./routes.component.css']
})
export class RoutesComponent implements OnInit {
  routes: Route[];
  routeName: string;
  account: UserAccount;

  constructor(private routeService: RouteService,
              private store: StoreService,
              public authService: AuthorizationService,
              private router: Router) {
  }

  ngOnInit() {
    this.account = this.store.currentAccount.account;
    this.getAllRoutes();
  }

  getAllRoutes() {
    this.routeService.getRoutes(this.account.accountId).subscribe(
      result => {
        this.routes = result;
      }
    );
  }

  searchRoutes() {

  }

  gotoDetail(routeId: number) {
    this.router.navigate(['/route/detail', routeId]);
  }

  gotoEdit(routeId: number) {
    this.router.navigate(['/route/edit', routeId]);
  }

  deleteRoute(routeId: number) {
    this.routeService.deleteRoute(routeId).subscribe(() => {
      this.getAllRoutes();
    });
  }
}
