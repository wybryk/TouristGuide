import {Component, OnInit} from '@angular/core';
import {Route} from '../../common/model/route';
import {Place} from '../../common/model/place';
import {PlaceService} from '../../place/place.service';
import {RouteService} from '../route.service';
import {StoreService} from '../../common/store.service';
import {DetailPlaceComponent} from '../../place/detail-place/detail-place.component';
import {MatDialog} from '@angular/material/dialog';
import {AuthorizationService} from '../../authorization/authorization.service';
import {PickList} from 'primeng/primeng';

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
              private store: StoreService,
              public authService: AuthorizationService,
              public dialog: MatDialog) {
  }

  ngOnInit() {
    this.route = new Route();
    this.route.places = new Array();
    this.getPlaces();
  }

  getPlaces() {
    this.placeService.getPlaces().subscribe(
      result => {
        this.places = result;
      }
    );
  }

  addRoute() {
    this.route.accountId = this.store.currentAccount.account.accountId;
    this.routeService.addRoute(this.route).subscribe(() => {});
  }

  gotoPlaceDetail(place: Place) {
    let placeDialogRef = this.dialog.open(DetailPlaceComponent, {
      width: '1000px',
      data: {
        placeId: place.placeId,
        canEdit: false
      }
    });
  }

  onChoseLocation(event) {
    /*this.place.latitude = event.coords.lat;
    this.place.longitude = event.coords.lng;
    this.locationChosen = true;*/
    console.log(event);
  }

  clear() {
    this.getPlaces();
    this.route.name = null;
    this.route.places = new Array();
  }
}
