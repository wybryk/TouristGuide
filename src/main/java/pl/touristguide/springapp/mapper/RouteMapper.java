package pl.touristguide.springapp.mapper;

import pl.touristguide.model.Account;
import pl.touristguide.model.Route;
import pl.touristguide.springapp.dto.RouteDTO;

import java.math.BigDecimal;

public class RouteMapper {
    public static Route toRoute(RouteDTO routeDTO, Account account) {
        Route route = new Route();
        route.setRouteId(routeDTO.getRouteId());
        route.setName(routeDTO.getName());
        route.setAccount(account);
        route.setRouteLength(new BigDecimal(0)/*routeDTO.getRouteLength()*/);
        route.setPlaces(PlaceMapper.toPlaceList(routeDTO.getPlaces()));
        return route;
    }

    public static RouteDTO toRouteDTO(Route route) {
        RouteDTO routeDTO = new RouteDTO();
        routeDTO.setRouteId(route.getRouteId());
        routeDTO.setName(route.getName());
        routeDTO.setRouteLength(new BigDecimal(0)/*routeDTO.getRouteLength()*/);
        routeDTO.setPlaces(PlaceMapper.toPlaceDTOQueue(route.getPlaces()));
        return routeDTO;
    }
}
