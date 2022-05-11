package com.codeinsight.flightreservation.flightreservation.repos;

import com.codeinsight.flightreservation.flightreservation.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
