package com.codeinsight.flightreservation.flightreservation.controllers;

import com.codeinsight.flightreservation.flightreservation.dto.ReservationRequest;
import com.codeinsight.flightreservation.flightreservation.entities.Flight;
import com.codeinsight.flightreservation.flightreservation.entities.Reservation;
import com.codeinsight.flightreservation.flightreservation.services.FlightService;
import com.codeinsight.flightreservation.flightreservation.services.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    FlightService flightService;
    @Autowired
    ReservationService reservationService;

    @RequestMapping("show-booking")
    public String showCompleteReservation(@RequestParam("flightId") Long flightId,
                                          ModelMap modelMap) throws Exception {
        LOGGER.info("Inside showCompleteReservation(), flight id: " + flightId);
        Flight flight = flightService.findFlightById(flightId);
        modelMap.addAttribute("flight", flight);
        LOGGER.info("Inside showCompleteReservation(), flight is " + flight);
        return "reservation/completeReservation";
    }

    @RequestMapping("book")
    public String completeReservation(ReservationRequest request, ModelMap modelMap) {
        LOGGER.info("Inside showCompleteReservation(), request is: " + request);
        Reservation reservation = reservationService.bookFlight(request);
        modelMap.addAttribute("msg",
                "Reservation created successfully with id: " + reservation.getId());
        LOGGER.info("Inside showCompleteReservation(), reservation created: " + reservation);
        return "reservation/reservationConfirmation";
    }
}
