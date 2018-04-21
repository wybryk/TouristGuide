import {Component, Inject, OnInit} from '@angular/core';

import 'rxjs/add/operator/switchMap';

import {PlaceService} from '../place.service';
import {Place} from '../../common/model/place';
import {Category} from '../../common/model/category';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';


@Component({
  selector: 'detail-place-dialog',
  templateUrl: './detail-place.component.html',
  styleUrls: ['./detail-place.component.css']
})

export class DetailPlaceDialog implements OnInit {
  place = new Place();
  canEdit: boolean = false;

  constructor(public dialogRef: MatDialogRef<DetailPlaceDialog>,
              @Inject(MAT_DIALOG_DATA) public data: any,
              private placeService: PlaceService) {
  }

  ngOnInit() {
    this.place.category = new Category();
    this.canEdit = this.data.canEdit;
    this.getPlace();
  }

  getPlace() {
    this.placeService.getPlace(this.data.placeId).subscribe(
      result => {
        this.place = result;
      }
    );
  }

  goBack() {
    this.dialogRef.close();
  }
}
