package net.iqbusiness.app.registrator.model.dao;

import net.iqbusiness.app.registrator.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepo extends JpaRepository<User, Integer> {
}
