import { Component, OnInit } from '@angular/core';
import {AuthorizationService} from '../../authorization/authorization.service';
import {Route} from '../../common/model/route';
import {Router} from '@angular/router';

@Component({
  selector: 'app-routes',
  templateUrl: './routes.component.html',
  styleUrls: ['./routes.component.css']
})
export class RoutesComponent implements OnInit {
  routes: Route[];
  routeName: string;
  constructor(public authService: AuthorizationService,
              private router: Router) { }

  ngOnInit() {
  }

  getAllRoutes() {

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

  }
}
