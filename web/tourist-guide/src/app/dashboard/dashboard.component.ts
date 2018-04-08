import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { Category } from '../common/model/category';
import { CategoryService } from '../category/category.service';

@Component({
    selector: 'dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.css']
})

export class DashboardComponent implements OnInit {
    categories: Category[];

    constructor(private categoryService: CategoryService, private router: Router) {}

    ngOnInit() {
        this.categoryService.getCategories().subscribe(val => {
            this.categories = val;
        }, error => {
            console.log(error);
        });
    }

    gotoPlaces(categoryId: number) {
        this.router.navigate(['/places', categoryId]);
    }
}
