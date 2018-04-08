import { Injectable } from '@angular/core';
import {Route} from '../common/model/route';
import {Observable} from 'rxjs/Observable';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class RouteService {
  private routeUrl = 'http://localhost:9090/touristguide/rest/routes';

  constructor(private http: HttpClient) { }

  addRoute(route: Route): Observable<any> {
    return this.http.post<any>(this.routeUrl, route);
  }

  getRoutes(userId: number): Observable<Route[]> {
    return this.http.get<any>(this.routeUrl + '/user' + userId);
  }

  getRoute(routeId: number): Observable<Route> {
    return this.http.get<any>(this.routeUrl + '/' + routeId);
  }

  updateRoute(route: Route): Observable<Route> {
    return this.http.put<any>(this.routeUrl + '/' + route.routeId, route);
  }

  deleteRoute(routeId: number): Observable<Route> {
    return this.http.delete<any>(this.routeUrl + '/' + routeId);
  }
}
