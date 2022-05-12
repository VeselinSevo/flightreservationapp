package com.codeinsight.flightreservation.flightreservation.services;

import com.codeinsight.flightreservation.flightreservation.entities.Flight;
import java.util.Date;
import java.util.List;

public interface FlightService {
    public List<Flight> findAllFLights();
    public List<Flight> findFLights(String to, String from, Date departureDate);
}
