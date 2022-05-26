package com.codeinsight.flightreservation.flightreservation.services;

import com.codeinsight.flightreservation.flightreservation.entities.Flight;
import java.util.Date;
import java.util.List;

public interface FlightService {
    public List<Flight> findAllFlights();
    public List<Flight> findFlights(String to, String from, Date departureDate);
    Flight findFlightById(Long flightId);
    Flight saveFlight(Flight saveFlight);
    void deleteFlight(Flight delFlight);
    Flight editFlight(Flight editFlight);
    public List<Flight> findFlightsWithParams(String flightNumber,
                                             String operatingAirlines, String departureCity,
                                             String arrivalCity);

}
