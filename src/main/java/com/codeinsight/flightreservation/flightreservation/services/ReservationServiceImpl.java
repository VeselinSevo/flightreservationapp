package com.codeinsight.flightreservation.flightreservation.services;

import com.codeinsight.flightreservation.flightreservation.controllers.FlightController;
import com.codeinsight.flightreservation.flightreservation.dto.ReservationRequest;
import com.codeinsight.flightreservation.flightreservation.entities.Flight;
import com.codeinsight.flightreservation.flightreservation.entities.Passenger;
import com.codeinsight.flightreservation.flightreservation.entities.Reservation;
import com.codeinsight.flightreservation.flightreservation.repos.PassengerRepository;
import com.codeinsight.flightreservation.flightreservation.repos.ReservationRepository;
import com.codeinsight.flightreservation.flightreservation.util.EmailUtil;
import com.codeinsight.flightreservation.flightreservation.util.PDFGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Value("${com.codeinsight.flightreservation.itinerary.dirpath}")
    private String ITINERARY_DIR;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

    @Autowired
    FlightService flightService;

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    PDFGenerator pdfGenerator;

    @Autowired
    EmailUtil emailUtil;
    @Override
    public Reservation findReservationById(Long id) {
        if(reservationRepository.findById(id).isPresent()) {
            return reservationRepository.findById(id).get();
        } throw new RuntimeException("Reservation not found");
    }

    @Override
    public Reservation bookFlight(ReservationRequest request) {
        LOGGER.info("Inside bookFlight()");
        //Make payment

        Long flightId = request.getFlightId();
        LOGGER.info("Fetching flight for flight id: " + flightId);
        Flight flight = flightService.findFlightById(flightId);

        Passenger passenger = new Passenger();
        passenger.setFirstName(request.getPassengerFirstName());
        passenger.setLastName(request.getPassengerLastName());
        passenger.setEmail(request.getPassengerEmail());
        passenger.setPhone(request.getPassengerPhone());
        LOGGER.info("Saving the passenger: " + passenger);
        Passenger savedPassenger = passengerRepository.save(passenger);

        Reservation reservation = new Reservation();
        reservation.setFlight(flight);
        reservation.setPassenger(savedPassenger);
        reservation.setCheckedIn(false);

        LOGGER.info("Saving the reservation: " + reservation);
        Reservation savedReservation = reservationRepository.save(reservation);
        pdfGenerator.generateItinerary(savedReservation,
                ITINERARY_DIR + savedReservation.getId() + ".pdf");
        LOGGER.info("Generating the itinerary ");
        emailUtil.sendItinerary(passenger.getEmail(),ITINERARY_DIR + savedReservation.getId() + ".pdf");
        LOGGER.info("Sending itinerary to email: ");
        return savedReservation;
    }
}
