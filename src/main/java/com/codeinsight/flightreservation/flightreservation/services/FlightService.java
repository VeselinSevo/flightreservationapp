package com.codeinsight.flightreservation.flightreservation.services;

import com.codeinsight.flightreservation.flightreservation.entities.Flight;
import java.util.Date;
import java.util.List;

public interface FlightService {
    public List<Flight> findAllFlights();
    public List<Flight> findFlights(String to, String from, Date departureDate);
}
