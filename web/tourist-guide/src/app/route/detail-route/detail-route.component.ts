import {Component, OnInit} from '@angular/core';
import {Route} from '../../common/model/route';
import {RouteService} from '../route.service';
import {ActivatedRoute, ParamMap, Router} from '@angular/router';
import {Location} from '@angular/common';

@Component({
  selector: 'app-detail-route',
  templateUrl: './detail-route.component.html',
  styleUrls: ['./detail-route.component.css']
})
export class DetailRouteComponent implements OnInit {
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

  goBack() {
    this.location.back();
  }
}
