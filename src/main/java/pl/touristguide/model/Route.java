package pl.touristguide.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "route", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RoutePlace> routePlaces;

    public Route() {
        this.routePlaces = new ArrayList<>();
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

    public List<RoutePlace> getRoutePlaces() {
        return routePlaces;
    }

    public void setRoutePlaces(List<RoutePlace> routePlaces) {
        this.routePlaces = routePlaces;
    }
}
