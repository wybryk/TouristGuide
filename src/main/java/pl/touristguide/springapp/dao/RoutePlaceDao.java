package pl.touristguide.springapp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.touristguide.model.RoutePlace;

@Repository
public interface RoutePlaceDao extends CrudRepository<RoutePlace, Long> {
}
