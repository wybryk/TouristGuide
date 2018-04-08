package pl.touristguide.springapp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.touristguide.model.Route;

@Repository
public interface RouteDao extends CrudRepository<Route, Long> {
}
