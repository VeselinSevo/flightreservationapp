package com.codeinsight.flightreservation.flightreservation.services;

import com.codeinsight.flightreservation.flightreservation.dto.ReservationRequest;
import com.codeinsight.flightreservation.flightreservation.entities.Flight;
import com.codeinsight.flightreservation.flightreservation.entities.Passenger;
import com.codeinsight.flightreservation.flightreservation.entities.Reservation;
import com.codeinsight.flightreservation.flightreservation.repos.PassengerRepository;
import com.codeinsight.flightreservation.flightreservation.repos.ReservationRepository;
import com.codeinsight.flightreservation.flightreservation.util.EmailUtil;
import com.codeinsight.flightreservation.flightreservation.util.PDFGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {


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
        //Make payment

        Long flightId = request.getFlightId();
        Flight flight = flightService.findFlightById(flightId);

        Passenger passenger = new Passenger();
        passenger.setFirstName(request.getPassengerFirstName());
        passenger.setLastName(request.getPassengerLastName());
        passenger.setEmail(request.getPassengerEmail());
        passenger.setPhone(request.getPassengerPhone());
        Passenger savedPassenger = passengerRepository.save(passenger);

        Reservation reservation = new Reservation();
        reservation.setFlight(flight);
        reservation.setPassenger(savedPassenger);
        reservation.setCheckedIn(false);

        Reservation savedReservation = reservationRepository.save(reservation);
        pdfGenerator.generateItinerary(savedReservation,
                "C://Users//Veselin//Desktop//Reservations//reservation_" + savedReservation.getId() + ".pdf");
        emailUtil.sendItinerary(passenger.getEmail(),"C://Users//Veselin//Desktop//Reservations//reservation_" + savedReservation.getId() + ".pdf");
        return savedReservation;
    }
}
