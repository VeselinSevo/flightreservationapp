package com.codeinsight.flightreservation.flightreservation.controllers;

import com.codeinsight.flightreservation.flightreservation.entities.Flight;
import com.codeinsight.flightreservation.flightreservation.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {

    @Autowired
    FlightService flightService;

    @RequestMapping("show-booking")
    public String showCompleteReservation(@RequestParam("flightId") Long flightId,
                                          ModelMap modelMap) throws Exception {

        Flight flight = flightService.findFlightById(flightId);
        modelMap.addAttribute("flight", flight);
        return "reservation/completeReservation";
    }
}
