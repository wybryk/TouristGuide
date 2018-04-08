import {Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute, ParamMap} from '@angular/router';
import {Location} from '@angular/common';

import 'rxjs/add/operator/switchMap';

import {PlaceService} from '../place.service';
import {Place} from '../../common/model/place';
import {Category} from '../../common/model/category';


@Component({
  selector: 'detail-place',
  templateUrl: './detail-place.component.html',
  styleUrls: ['./detail-place.component.css']
})

export class DetailPlaceComponent implements OnInit {
  place = new Place();

  constructor(private router: Router,
              private route: ActivatedRoute,
              private location: Location,
              private placeService: PlaceService) {
  }

  ngOnInit() {
    this.place.category = new Category();
    this.route.paramMap.switchMap((params: ParamMap) =>
      this.placeService.getPlace(+params.get('id'))).subscribe(val => this.place = val);
  }

  gotoPlaceEdit(placeId: number) {
    this.router.navigate(['/edit', placeId]);
  }

  goBack() {
    this.location.back();
  }
}
