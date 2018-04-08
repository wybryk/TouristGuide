package pl.touristguide.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "ROUTE")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ROUTE_ID")
    private Long routeId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "ROUTE_LENGTH")
    private BigDecimal routeLength;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

    /*OneToMany(mappedBy = "route", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Queue<RoutePlace> routePlaces;*/

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "ROUTE_PLACE", joinColumns = {@JoinColumn(name = "ROUTE_ID")}, inverseJoinColumns = {@JoinColumn(name = "PLACE_ID")})
    private List<Place> places;

    public Route() {
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getRouteLength() {
        return routeLength;
    }

    public void setRouteLength(BigDecimal routeLength) {
        this.routeLength = routeLength;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    /*public Queue<RoutePlace> getRoutePlaces() {
        return routePlaces;
    }

    public void setRoutePlaces(Queue<RoutePlace> routePlaces) {
        this.routePlaces = routePlaces;
    }*/

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    @Override
    public String toString() {
        return "Route{" +
                "routeId=" + routeId +
                ", name='" + name + '\'' +
                ", routeLength=" + routeLength +
                ", account=" + account +
                ", places=" + places +
                '}';
    }
}
