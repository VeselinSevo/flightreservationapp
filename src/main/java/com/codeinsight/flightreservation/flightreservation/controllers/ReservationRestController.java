package com.codeinsight.flightreservation.flightreservation.controllers;

import com.codeinsight.flightreservation.flightreservation.dto.ReservationUpdateRequest;
import com.codeinsight.flightreservation.flightreservation.entities.Reservation;
import com.codeinsight.flightreservation.flightreservation.repos.ReservationRepository;
import com.codeinsight.flightreservation.flightreservation.services.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationRestController.class);

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    ReservationService reservationService;

    @RequestMapping("reservations/{id}")
    public Reservation findReservation(@PathVariable("id") Long id) {
        LOGGER.info("Inside findReservation() for id: " + id);
        return reservationService.findReservationById(id);
    }

    @RequestMapping("reservations")
    public Reservation updateReservation(@RequestBody ReservationUpdateRequest request) {
        Reservation reservation = reservationService.findReservationById(request.getId());
        reservation.setNumberOfBags(request.getNumberOfBags());
        reservation.setCheckedIn(request.getCheckedIn());
        Reservation updatedReservation = reservationRepository.save(reservation);
        LOGGER.info("Inside updateReservation(), updating reservation with id: " + request.getId() + "to " + request);
        return updatedReservation;
    }
}
