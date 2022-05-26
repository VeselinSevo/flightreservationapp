package com.codeinsight.flightreservation.flightreservation.services;

import com.codeinsight.flightreservation.flightreservation.entities.Flight;
import com.codeinsight.flightreservation.flightreservation.repos.FlightRepository;
import com.codeinsight.flightreservation.flightreservation.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    UserService userService;

    @Autowired
    EmailUtil emailUtil;

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public List<Flight> findAllFlights() {
        return flightRepository.findAll();
    }

    public List<Flight> findFlights(String to, String from, Date departureDate) {
        return flightRepository.findFlights(to, from, departureDate);
    }

    @Override
    public Flight findFlightById(Long flightId) {
        if(flightRepository.findById(flightId).isPresent()){
            return flightRepository.findById(flightId).get();
        } throw new RuntimeException("Flight not found");
    }

    @Override
    public Flight saveFlight(Flight saveFlight) {
        Flight savedFlight = flightRepository.save(saveFlight);
        for (String email: userService.findSubscribedUsersEmails()){
            emailUtil.sendNewFlightAddedToEmail(email, savedFlight);
        }
        return savedFlight;
    }

    @Override
    public void deleteFlight(Flight delFlight) {
        flightRepository.delete(delFlight);
    }

    @Override
    public Flight editFlight(Flight editFlight) {
        Flight newFlight = findFlightById(editFlight.getId());
        newFlight.setFlightNumber(editFlight.getFlightNumber());
        newFlight.setOperatingAirlines(editFlight.getOperatingAirlines());
        newFlight.setDepartureCity(editFlight.getDepartureCity());
        newFlight.setArrivalCity(editFlight.getArrivalCity());
        newFlight.setDepartureDate(editFlight.getDepartureDate());
        newFlight.setEstimatedDepartureTime(editFlight.getEstimatedDepartureTime());
        return flightRepository.save(newFlight);
    }

    @Override
    public List<Flight> findFlightsWithParams(String flightNumber, String operatingAirlines,
                                              String departureCity, String arrivalCity) {
        return flightRepository.findFlightsWithParams(flightNumber, operatingAirlines,
                departureCity, arrivalCity);
    }
}
