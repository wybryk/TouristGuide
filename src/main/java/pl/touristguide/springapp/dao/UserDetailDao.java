package pl.touristguide.springapp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.touristguide.model.UserDetail;

@Repository
public interface UserDetailDao extends CrudRepository<UserDetail, Long> {
}
