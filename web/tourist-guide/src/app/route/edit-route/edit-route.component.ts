import {Component, OnInit} from '@angular/core';
import {Route} from '../../common/model/route';
import {RouteService} from '../route.service';
import {ActivatedRoute, ParamMap, Router} from '@angular/router';
import {Location} from '@angular/common';
import {Place} from '../../common/model/place';
import {StoreService} from '../../common/store.service';
import {PlaceService} from '../../place/place.service';
import {MatDialog} from '@angular/material/dialog';
import {DetailPlaceComponent} from '../../place/detail-place/detail-place.component';
import {Category} from '../../common/model/category';
import {CategoryService} from '../../category/category.service';

@Component({
  selector: 'app-edit-route',
  templateUrl: './edit-route.component.html',
  styleUrls: ['./edit-route.component.css']
})
export class EditRouteComponent implements OnInit {
  route = new Route();
  allPlaces: Place[];
  places: Place[];
  categories: Category[];
  selectedCategory: Category;
  filterPlaces: Place[] = new Array();

  latitude = 50.866025381805336;
  longitude = 20.628521202597767;

  constructor(private router: Router,
              private activatedRoute: ActivatedRoute,
              private location: Location,
              private routeService: RouteService,
              private  placeService: PlaceService,
              private categoryService: CategoryService,
              public dialog: MatDialog) {
  }

  ngOnInit() {
    this.activatedRoute.paramMap.switchMap((params: ParamMap) =>
      this.routeService.getRoute(+params.get('id'))).subscribe(
      val => {
        this.route = val;
        this.getPlaces();
        this.getCategories();
      }
    );
  }

  getPlaces() {
    this.placeService.getPlaces().subscribe(
      result => {
        this.places = result;
        this.filtrPlaces();
      }
    );
  }

  getCategories() {
    this.categoryService.getCategories().subscribe(val => {
      this.categories = val;
    });
  }

  filtrPlaces() {
    this.places.forEach(
      tmpPlace => {
        let isUsed = false;
        this.route.places.forEach(
          tmpRoutePlace => {
            if (tmpPlace.placeId == tmpRoutePlace.placeId)
              isUsed = true;
            }
        );
        if(!isUsed) {
          this.filterPlaces.push(tmpPlace);
        }
      }
    );
  }

  editRoute() {

  }

  gotoPlaceDetail(place: Place) {
    let placeDialogRef = this.dialog.open(DetailPlaceComponent, {
      width: '500px',
      data: {place: place}
    });

    placeDialogRef.afterClosed().subscribe(result => {
      console.log("close");
    });
  }

  filerPlaces() {
    if(this.selectedCategory) {
      this.places = this.allPlaces.filter(tmpPlace => tmpPlace.category.categoryId === this.selectedCategory.categoryId)
    }
    else {
      this.places = this.allPlaces;
    }
  }

  clear() {
    this.route.name = null;
    this.route.places = new Array();
  }

  goBack() {
    this.location.back();
  }
}
