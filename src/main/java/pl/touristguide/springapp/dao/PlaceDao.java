package pl.touristguide.springapp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.touristguide.model.Place;

@Repository
public interface PlaceDao extends CrudRepository<Place, Long> {
}
