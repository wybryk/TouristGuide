import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';


import {DashboardComponent} from '../dashboard/dashboard.component';
import {AddPlaceComponent} from '../place/add-place/add-place.component';
import {PlacesComponent} from '../place/places/places.component';
import {DetailPlaceComponent} from '../place/detail-place/detail-place.component';
import {PagesComponent} from '../pages/pages.component';
import {AddCategoryComponent} from '../category/add-category/add-category.component';
import {RegistrationComponent} from '../authorization/registration/registration.component';
import {LoginComponent} from '../authorization/login/login.component';
import {DetailUserComponent} from '../user/detail-user/detail-user.component';
import {EditUserComponent} from '../user/edit-user/edit-user.component';
import {EditPlaceComponent} from '../place/edit-place/edit-place.component';
import {CategoriesComponent} from '../category/categories/categories.component';
import {AddRouteComponent} from '../route/add-route/add-route.component';
import {EditRouteComponent} from '../route/edit-route/edit-route.component';
import {RoutesComponent} from '../route/routes/routes.component';
import {DetailRouteComponent} from '../route/detail-route/detail-route.component';
import {MyUserComponent} from '../user/my-user/my-user.component';


const routes: Routes = [
  {
    path: '',
    redirectTo: '/dashboard',
    pathMatch: 'full'
  },
  {
    path: 'dashboard',
    component: DashboardComponent
  },
  {
    path: 'place/add',
    component: AddPlaceComponent
  },
  {
    path: 'places',
    component: PlacesComponent
  },
  {
    path: 'places/:id',
    component: PlacesComponent
  },
  {
    path: 'place/detail/:id',
    component: DetailPlaceComponent
  },
  {
      path: 'place/edit/:id',
      component: EditPlaceComponent
  },
  {
    path: 'categories',
    component: CategoriesComponent
  },
  {
    path: 'category/add',
    component: AddCategoryComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegistrationComponent
  },
  {
    path: 'user/detail',
    component: DetailUserComponent
  },
  {
    path: 'user/edit',
    component: EditUserComponent
  }
  ,{
    path: 'route/add',
    component: AddRouteComponent
  },
  {
    path: 'route/detail/:id',
    component: DetailRouteComponent
  },
  {
    path: 'route/edit/:id',
    component: EditRouteComponent
  },
  {
    path: 'my-user',
    component: MyUserComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
