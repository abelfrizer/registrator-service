package net.iqbusiness.app.registrator.model.dao;

import net.iqbusiness.app.registrator.model.dto.UserSearchDTO;
import net.iqbusiness.app.registrator.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserJpaRepo extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u " +
            " WHERE u.userIdentity = :userIdentity")
    Optional<User> findByUserIdentity(@Param("userIdentity") String userIdentity);

    @Query("SELECT u FROM User u " +
            " ORDER BY u.dateCreated DESC ")
    Page<User> findAll(Pageable pageable);

    //    @Query("SELECT u FROM User u WHERE u.uuid = :uuid")
    Optional<User> findByUuid(@Param("uuid") String uuid);

    @Query("SELECT u FROM User u " +
            " WHERE UPPER(u.firstName) LIKE :#{#dto.firstName} " +
            "    AND UPPER(u.surname) LIKE :#{#dto.surname} " +
            "    AND u.dateCreated BETWEEN :#{#dto.startDate} AND :#{#dto.endDate}" +
            " ORDER BY u.dateCreated DESC ")
    List<User> searchUsers(Pageable pageable, @Param("dto") UserSearchDTO dto);
}
