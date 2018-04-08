package pl.touristguide.model;

import javax.persistence.*;

@Entity
@Table(name = "ROUTE_PLACE")
public class RoutePlace {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ROUTE_PLACE_ID")
    private Long routePlaceId;

    @Column(name = "NUMBER_ON_ROUTE", nullable = false)
    private Integer numberOnRoute;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @MapsId("routeId")
    @JoinColumn(name = "ROUTE_ID")
    private Route route;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @MapsId("placeId")
    @JoinColumn(name = "PLACE_ID")
    private Place place;

    public RoutePlace() {
    }

    public Long getRoutePlaceId() {
        return routePlaceId;
    }

    public void setRoutePlaceId(Long routePlaceId) {
        this.routePlaceId = routePlaceId;
    }

    public Integer getNumberOnRoute() {
        return numberOnRoute;
    }

    public void setNumberOnRoute(Integer numberOnRoute) {
        this.numberOnRoute = numberOnRoute;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
