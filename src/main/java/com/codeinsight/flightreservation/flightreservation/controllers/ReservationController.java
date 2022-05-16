package com.codeinsight.flightreservation.flightreservation.controllers;

import com.codeinsight.flightreservation.flightreservation.dto.ReservationRequest;
import com.codeinsight.flightreservation.flightreservation.entities.Flight;
import com.codeinsight.flightreservation.flightreservation.entities.Reservation;
import com.codeinsight.flightreservation.flightreservation.services.FlightService;
import com.codeinsight.flightreservation.flightreservation.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {

    @Autowired
    FlightService flightService;
    @Autowired
    ReservationService reservationService;

    @RequestMapping("show-booking")
    public String showCompleteReservation(@RequestParam("flightId") Long flightId,
                                          ModelMap modelMap) throws Exception {

        Flight flight = flightService.findFlightById(flightId);
        modelMap.addAttribute("flight", flight);
        return "reservation/completeReservation";
    }

    @RequestMapping("book")
    public String completeReservation(ReservationRequest request, ModelMap modelMap) {
        Reservation reservation = reservationService.bookFlight(request);
        modelMap.addAttribute("msg",
                "Reservation created successfully with id: " + reservation.getId());
        return "reservation/reservationConfirmation";
    }
}
