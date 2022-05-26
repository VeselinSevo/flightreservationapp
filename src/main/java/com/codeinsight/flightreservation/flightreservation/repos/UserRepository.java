package com.codeinsight.flightreservation.flightreservation.repos;

import com.codeinsight.flightreservation.flightreservation.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findUserByEmail(@Param("email") String email);

    @Query("SELECT u.email FROM User u WHERE u.subscribed = subscribed")
    List<String> findSubscribedUsersEmails();

}
