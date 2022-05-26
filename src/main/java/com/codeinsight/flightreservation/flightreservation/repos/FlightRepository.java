package com.codeinsight.flightreservation.flightreservation.repos;

import com.codeinsight.flightreservation.flightreservation.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Date;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
@Query(value = "SELECT * FROM flight WHERE departure_city = :departure_city " +
        "AND arrival_city = :arrival_city " +
        "AND date_of_departure = :date_of_departure", nativeQuery = true)
    List<Flight> findFlights(@Param("departure_city") String from,
                             @Param("arrival_city") String to,
                             @Param("date_of_departure") Date departureDate);

@Query(value = "SELECT * FROM flight WHERE flight_number LIKE :flightNumber% " +
        "AND operating_airlines LIKE :operatingAirlines% " +
        "AND departure_city LIKE :departureCity% " +
        "AND arrival_city LIKE :arrivalCity%", nativeQuery = true)
    List<Flight> findFlightsWithParams(@Param("flightNumber") String flightNumber,
                                       @Param("operatingAirlines") String operatingAirlines,
                                       @Param("departureCity") String departureCity,
                                       @Param("arrivalCity") String arrivalCity);
}
