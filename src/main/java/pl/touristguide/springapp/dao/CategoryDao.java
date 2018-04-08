package pl.touristguide.springapp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.touristguide.model.Category;

@Repository
public interface CategoryDao extends CrudRepository<Category, Long> {
}
