import {Component, OnInit} from '@angular/core';
import {FormControl, Validators} from '@angular/forms';


import {Place} from '../../common/model/place';
import {Category} from '../../common/model/category';
import {CategoryService} from '../../category/category.service';
import {PlaceService} from '../place.service';


@Component({
  selector: 'add-place',
  templateUrl: './add-place.component.html',
  styleUrls: ['./add-place.component.css']
})

export class AddPlaceComponent implements OnInit {
  place = new Place();
  categories: Category[];
  categoryControl = new FormControl('', [Validators.required]);
  locationChosen: boolean = false;

  constructor(private categoryService: CategoryService, private placeService: PlaceService) {
  }

  ngOnInit() {
    this.categoryService.getCategories().subscribe(val => this.categories = val);
    this.place.latitude = 50.866025381805336;
    this.place.longitude = 20.628521202597767;
  }

  addPlace() {
    this.placeService.addPlace(this.place).subscribe(val => {
      this.clear();
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
    this.place.latitude = event.coords.lat;
    this.place.longitude = event.coords.lng;
    this.locationChosen = true;
    console.log(event);
  }


  clear() {
    this.place.category = null;
    this.place.name = null;
    this.place.description = null;
    this.place.image = null;
  }
}
