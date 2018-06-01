import {Component, Inject, OnInit} from '@angular/core';
import {PlaceService} from '../place.service';
import {Place} from '../../common/model/place';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {Category} from '../../common/model/category';
import {CategoryService} from '../../category/category.service';
import {FormControl, Validators} from '@angular/forms';

@Component({
  selector: 'app-edit-place',
  templateUrl: './edit-place.component.html',
  styleUrls: ['./edit-place.component.css']
})
export class EditPlaceComponent implements OnInit {
  place = new Place();
  categories: Category[];
  categoryControl = new FormControl('', [Validators.required]);

  constructor(public dialogRef: MatDialogRef<EditPlaceComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any,
              private placeService: PlaceService,
              private categoryService: CategoryService) {
  }

  ngOnInit() {
    this.place.category = new Category();
    this.getPlace();
  }

  getPlace() {
    this.placeService.getPlace(this.data.placeId).subscribe(
      result => {
        this.place = result;
        this.getCategories();
      }
    );
  }

  getCategories() {
    this.categoryService.getCategories().subscribe(val => {
      this.categories = val;
      val.forEach(tmpCategory => {
        if(tmpCategory.categoryId === this.place.category.categoryId)
          this.place.category = tmpCategory;
      });
    });
  }

  editPlace() {
    this.placeService.updatePlace(this.place).subscribe(val => {
    }, error => {
      console.log(error);
    });
  }

  readImage(event: any) {
    if (event.target.files && event.target.files[0]) {
      var reader = new FileReader();

      reader.onload = (event: any) => {
        this.place.image = event.target.result;
      };

      reader.readAsDataURL(event.target.files[0]);
    }
  }

  onChoseLocation(event) {
    this.place.lat = event.coords.lat;
    this.place.lng = event.coords.lng;
  }

  goBack() {
    this.dialogRef.close();
  }

}
