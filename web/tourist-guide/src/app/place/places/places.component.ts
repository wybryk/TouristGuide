import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, ParamMap, Router} from '@angular/router';

import {CategoryService} from '../../category/category.service';
import {PlaceService} from '../place.service';
import {Place} from '../../common/model/place';
import {Category} from '../../common/model/category';
import {Observable} from 'rxjs/Observable';
import {AuthorizationService} from '../../authorization/authorization.service';

@Component({
  selector: 'places',
  templateUrl: './places.component.html',
  styleUrls: ['./places.component.css']
})

export class PlacesComponent implements OnInit {
  places: Place[];
  categories: Category[];
  searchedPlace = new Place();

  constructor(private router: Router,
              private route: ActivatedRoute,
              private placeService: PlaceService,
              private categoryService: CategoryService,
              public authService: AuthorizationService) {
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
      return this.placeService.getPlaces();
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
    this.router.navigate(['/place/detail', placeId]);
  }

  searchPlaces() {
    this.placeService.searchPlaces(this.searchedPlace).subscribe(
      val => {
        this.places = val;
      }
    );
  }

  getAllPlaces() {
    this.placeService.getPlaces().subscribe(
      val => {
        this.places = val;
        this.searchedPlace = new Place();
      }
    );
  }

  deletePlace(placeId: number) {
    this.placeService.deletePlace(placeId).subscribe(() => {
      this.places.filter(value => {
        value.placeId !== placeId;
      });
    });
  }

  gotoEdit(placeId: number) {
    this.router.navigate(['/place/edit', placeId]);
  }
}
