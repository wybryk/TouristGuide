package pl.touristguide.springapp.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Queue;

public class RouteDTO implements Serializable {
    private Long routeId;
    private String name;
    private AccountDTO account;
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

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
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
