import { Component, OnInit } from '@angular/core';
import {Category} from '../../common/model/category';
import {CategoryService} from '../category.service';
import {AuthorizationService} from '../../authorization/authorization.service';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements OnInit {
  categories: Category[];
  constructor(private categoryService: CategoryService,
              public authService: AuthorizationService) { }

  ngOnInit() {
    this.getCategories();
  }

  private getCategories() {
    this.categoryService.getCategories().subscribe(
      result => {
        this.categories = result;
      }
    );
  }

  gotoEdit(categoryId: number) {
    console.warn("gotoEdit");
  }

  deleteCategory(categoryId: number) {
    this.categoryService.deleteCategory(categoryId).subscribe(() => {
      this.categories.filter(value => {
        value.categoryId !== categoryId;
      });
    });
  }

}
