import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AgmCoreModule } from '@agm/core';

import { AppRoutingModule } from './routing/app-routing.module';

import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AddPlaceComponent } from './place/add-place/add-place.component';
import { DetailPlaceComponent } from './place/detail-place/detail-place.component';
import { PlacesComponent } from './place/places/places.component';
import { PagesComponent } from './pages/pages.component';


import { MatCardModule } from '@angular/material/card';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatMenuModule } from '@angular/material/menu';
import { MatGridListModule } from '@angular/material/grid-list';

import { PlaceService } from './place/place.service';
import { CategoryService } from './category/category.service';

import { AddCategoryComponent } from './category/add-category/add-category.component';
import { EditCategoryComponent } from './category/edit-category/edit-category.component';
import { CategoriesComponent } from './category/categories/categories.component';
import { EditPlaceComponent } from './place/edit-place/edit-place.component';
import { LoginComponent } from './authorization/login/login.component';
import { RegistrationComponent } from './authorization/registration/registration.component';
import { AuthorizationService } from './authorization/authorization.service';
import { StoreService } from './common/store.service';
import { EditUserComponent } from './user/edit-user/edit-user.component';
import { DetailUserComponent } from './user/detail-user/detail-user.component';
import { AddRouteComponent } from './route/add-route/add-route.component';
import { EditRouteComponent } from './route/edit-route/edit-route.component';
import { DetailRouteComponent } from './route/detail-route/detail-route.component';
import { RoutesComponent } from './route/routes/routes.component';
import {RouteService} from './route/route.service';
import {DndModule} from 'ng2-dnd';
import {AgmDirectionModule} from 'agm-direction';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    AddPlaceComponent,
    DetailPlaceComponent,
    PlacesComponent,
    PagesComponent,
    AddCategoryComponent,
    EditCategoryComponent,
    CategoriesComponent,
    EditPlaceComponent,
    LoginComponent,
    RegistrationComponent,
    EditUserComponent,
    DetailUserComponent,
    AddRouteComponent,
    EditRouteComponent,
    DetailRouteComponent,
    RoutesComponent
  ],
  imports: [
    CommonModule,
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBmWYu91eLP568GTyJdxz3yrM7LdauTS5U'
    }),
    AgmDirectionModule,
    AppRoutingModule,
    MatCardModule,
    MatSidenavModule,
    MatListModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatToolbarModule,
    MatMenuModule,
    MatGridListModule,
    DndModule.forRoot()
  ],
  providers: [
    PlaceService,
    CategoryService,
    RouteService,
    AuthorizationService,
    StoreService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
