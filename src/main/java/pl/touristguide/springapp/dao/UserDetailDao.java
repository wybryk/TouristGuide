package pl.touristguide.springapp.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.touristguide.model.UserDetail;

import java.util.Optional;

@Repository
public interface UserDetailDao extends CrudRepository<UserDetail, Long> {
    @Query("SELECT u FROM UserDetail u WHERE u.email like ?1")
    Optional<UserDetail> findUserDetailsByEmail(@Param("email") String email);
}
