package com.codeinsight.flightreservation.flightreservation.repos;

import com.codeinsight.flightreservation.flightreservation.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
