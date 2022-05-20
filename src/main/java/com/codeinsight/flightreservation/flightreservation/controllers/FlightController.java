package com.codeinsight.flightreservation.flightreservation.controllers;

import com.codeinsight.flightreservation.flightreservation.entities.Flight;
import com.codeinsight.flightreservation.flightreservation.entities.User;
import com.codeinsight.flightreservation.flightreservation.services.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Date;
import java.util.List;

@Controller
public class FlightController {

    @Autowired
    FlightService flightService;

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightController.class);

    @RequestMapping("find-flights")
    public String findFlights(@RequestParam("from") String from,
                              @RequestParam("to") String to,
                              @RequestParam("departureDate") @DateTimeFormat(pattern="dd-MM-yyyy") Date departureDate,
                              ModelMap modelMap) {
        LOGGER.info("Inside findFlights() FROM: " + from + " TO: " + to + "DEPARTURE DATE: "+ departureDate);
        List<Flight> flights = flightService.findFlights(from, to, departureDate);
        if(flights.isEmpty()) {
            return "flights/displayFlightsEmpty";
        }
        modelMap.addAttribute("flights", flights);
        LOGGER.info("Inside findFlights(), flights found are" + flights);
        return "flights/displayFlights";
    }
}
