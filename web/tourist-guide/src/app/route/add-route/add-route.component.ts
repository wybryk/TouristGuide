import {Component, OnInit} from '@angular/core';
import {Route} from '../../common/model/route';
import {Place} from '../../common/model/place';
import {PlaceService} from '../../place/place.service';
import {RouteService} from '../route.service';
import {StoreService} from '../../common/store.service';

@Component({
  selector: 'app-add-route',
  templateUrl: './add-route.component.html',
  styleUrls: ['./add-route.component.css']
})
export class AddRouteComponent implements OnInit {
  route: Route;
  places: Place[];
  latitude = 50.866025381805336;
  longitude = 20.628521202597767;

  constructor(private routeService: RouteService,
              private  placeService: PlaceService,
              private store: StoreService) {
  }

  ngOnInit() {
    this.route = new Route();
    this.route.places = new Array();
    this.getPlaces();
    this.route.account = this.store.currentAccount.account;
  }

  getPlaces() {
    this.placeService.getPlaces().subscribe(
      result => {
        this.places = result;
      }
    );
  }

  addRoute() {
    this.routeService.addRoute(this.route).subscribe(() => {});
  }

  onChoseLocation(event) {
    /*this.place.latitude = event.coords.lat;
    this.place.longitude = event.coords.lng;
    this.locationChosen = true;*/
    console.log(event);
  }

  clear() {
    this.route.name = null;
    this.route.places = new Array();
  }
}
