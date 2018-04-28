package pl.touristguide.springapp.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Queue;

public class RouteDTO implements Serializable {
    private Long routeId;
    private String name;
    private Long accountId;
    private BigDecimal routeLength;
    private Queue<PlaceDTO> places;

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

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getRouteLength() {
        return routeLength;
    }

    public void setRouteLength(BigDecimal routeLength) {
        this.routeLength = routeLength;
    }

    public Queue<PlaceDTO> getPlaces() {
        return places;
    }

    public void setPlaces(Queue<PlaceDTO> places) {
        this.places = places;
    }
}
