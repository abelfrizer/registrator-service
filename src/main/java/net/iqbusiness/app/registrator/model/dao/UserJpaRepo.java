package net.iqbusiness.app.registrator.model.dao;

import net.iqbusiness.app.registrator.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserJpaRepo extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u " +
            " WHERE u.userIdentity = :userIdentity")
    Optional<User> findByUserIdentity(String userIdentity);

}
