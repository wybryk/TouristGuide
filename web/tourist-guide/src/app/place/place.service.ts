import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Place } from '../common/model/place';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class PlaceService {
    private placeUrl = 'http://localhost:9090/touristguide/rest/places';

    constructor(private http: HttpClient) {}

    getPlace(PlaceId: number): Observable<Place> {
        return this.http.get<Place>(this.placeUrl + '/' + PlaceId);
    }

    getPlaces(): Observable<Place[]> {
        return this.http.get<Place[]>(this.placeUrl);
    }

    addPlace(place: Place): Observable<any> {
        return this.http.post<any>(this.placeUrl, place);
    }

    updatePlace(place: Place): Observable<any> {
        return this.http.put<any>(this.placeUrl + '/' + place.placeId, place);
    }

    deletePlace(placeId: number): Observable<any> {
        return this.http.delete<any>(this.placeUrl + '/' + placeId);
    }

    searchPlaces(place: Place): Observable<Place[]> {
        return this.http.post<Place[]>(this.placeUrl + "/search", place);
    }

    searchPlacesByCategoryId(categoryId: number): Observable<Place[]> {
        return this.http.get<Place[]>(this.placeUrl + "/search/category/" + categoryId);
    }

    getUserPlaces(accountId: number): Observable<Place[]> {
      return this.http.get<Place[]>(this.placeUrl + '/search/user/' + accountId);
    }
}
