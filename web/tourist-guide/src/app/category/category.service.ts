import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { Category } from '../common/model/category';

@Injectable()
export class CategoryService{
    private categoryUrl = 'http://localhost:9090/touristguide/rest/categories';

    constructor(private http: HttpClient) {}

    getCategories(): Observable<Category[]> {
        return this.http.get<Category[]>(this.categoryUrl);
    }

    addCategory(category: Category): Observable<any> {
        return this.http.post<any>(this.categoryUrl, category);
    }

    deleteCategory(categoryId: number): Observable<any> {
      return this.http.delete(this.categoryUrl + "/" + categoryId);
    }

}
