import { Component } from '@angular/core';
import {FormControl, Validators} from '@angular/forms';
import { Category } from '../../common/model/category';
import { CategoryService } from '../category.service';


@Component({
    selector: 'add-category',
    templateUrl: './add-category.component.html',
    styleUrls: ['./add-category.component.css']
})

export class AddCategoryComponent {
    category = new Category();

    constructor(private categoryService: CategoryService) {}


    addCategory() {
        this.categoryService.addCategory(this.category).subscribe(val => {
            this.clear();
        }, error => {
            console.log(error);
        });
    }

    readImage(event: any) {
        if (event.target.files && event.target.files[0]) {
            var reader = new FileReader();

            reader.onload = (event: any) => {
                this.category.image = event.target.result;
            }

            reader.readAsDataURL(event.target.files[0]);
        }
    }

    clear() {
        this.category.name = null;
        this.category.image = null;
    }
}
