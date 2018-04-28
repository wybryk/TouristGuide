import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, ParamMap, Router} from '@angular/router';

import {CategoryService} from '../../category/category.service';
import {PlaceService} from '../place.service';
import {Place} from '../../common/model/place';
import {Category} from '../../common/model/category';
import {Observable} from 'rxjs/Observable';
import {AuthorizationService} from '../../authorization/authorization.service';
import {DetailPlaceComponent} from '../detail-place/detail-place.component';
import {EditPlaceComponent} from '../edit-place/edit-place.component';
import {MatDialog} from '@angular/material/dialog';
import {StoreService} from '../../common/store.service';

@Component({
  selector: 'app-places',
  templateUrl: './places.component.html',
  styleUrls: ['./places.component.css']
})

export class PlacesComponent implements OnInit {
  @Input() userPlaces: boolean;
  places: Place[];
  categories: Category[];
  searchedPlace = new Place();

  constructor(private router: Router,
              private route: ActivatedRoute,
              private placeService: PlaceService,
              private categoryService: CategoryService,
              public authService: AuthorizationService,
              private store: StoreService,
              public dialog: MatDialog) {
  }

  ngOnInit() {
    this.categoryService.getCategories().subscribe(val => {
      this.categories = val;
      this.route.paramMap.switchMap((params: ParamMap) => this.getPlaces(+params.get('id'))).subscribe(val => this.places = val);
    });
  }

  getPlaces(categoryId: any): Observable<Place[]> {
    if (categoryId) {
      this.findSearchedCategory(categoryId);
      return this.placeService.searchPlacesByCategoryId(categoryId);
    }
    else {
      if (this.userPlaces) {
        return this.placeService.getUserPlaces(this.store.currentAccount.account.accountId);
      }
      else {
        return this.placeService.getPlaces();
      }
    }
  }

  findSearchedCategory(categoryId: number) {
    this.categories.forEach(tmpCategory => {
      if (tmpCategory.categoryId == categoryId) {
        this.searchedPlace.category = tmpCategory;
      }
    });
  }

  gotoDetail(placeId: number) {
    let placeDialogRef = this.dialog.open(DetailPlaceComponent, {
      width: '1000px',
      data: {
        placeId: placeId,
        canEdit: false
      }
    });
  }

  gotoEdit(placeId: number) {
    let placeDialogRef = this.dialog.open(EditPlaceComponent, {
      width: '1000px',
      data: {
        placeId: placeId
      }
    });
  }

  searchPlaces() {
    this.placeService.searchPlaces(this.searchedPlace).subscribe(
      val => {
        this.places = val;
      }
    );
  }

  getAllPlaces() {
    this.router.navigate(['/places']);
  }

  deletePlace(placeId: number) {
    this.placeService.deletePlace(placeId).subscribe(() => {
      this.places.filter(value => {
        value.placeId !== placeId;
      });
    });
  }
}
