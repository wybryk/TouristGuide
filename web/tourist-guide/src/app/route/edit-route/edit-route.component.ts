import {Component, OnInit} from '@angular/core';
import {Route} from '../../common/model/route';
import {RouteService} from '../route.service';
import {ActivatedRoute, ParamMap, Router} from '@angular/router';
import {Location} from '@angular/common';

@Component({
  selector: 'app-edit-route',
  templateUrl: './edit-route.component.html',
  styleUrls: ['./edit-route.component.css']
})
export class EditRouteComponent implements OnInit {
  route = new Route();
  latitude = 50.866025381805336;
  longitude = 20.628521202597767;

  constructor(private router: Router,
              private activatedRoute: ActivatedRoute,
              private location: Location,
              private routeService: RouteService) {
  }

  ngOnInit() {
    this.activatedRoute.paramMap.switchMap((params: ParamMap) =>
      this.routeService.getRoute(+params.get('id'))).subscribe(val => this.route = val);
  }

  editRoute() {

  }

  goBack() {
    this.location.back();
  }
}
