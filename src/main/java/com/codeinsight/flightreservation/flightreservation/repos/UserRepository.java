package com.codeinsight.flightreservation.flightreservation.repos;

import com.codeinsight.flightreservation.flightreservation.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
