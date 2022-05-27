package com.codeinsight.flightreservation.flightreservation.controllers;

import com.codeinsight.flightreservation.flightreservation.entities.Flight;
import com.codeinsight.flightreservation.flightreservation.services.FlightService;
import com.codeinsight.flightreservation.flightreservation.services.UserService;
import com.codeinsight.flightreservation.flightreservation.util.EmailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class FlightController {

    @Autowired
    EmailUtil emailUtil;

    @Autowired
    UserService userService;

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
    @RequestMapping("admin/display-flights")
    public String showDisplayFlights(ModelMap modelMap){
        List<Flight> allFlights = flightService.findAllFlights();
        modelMap.addAttribute("flights", allFlights);
        modelMap.addAttribute("flightNumber", null);
        modelMap.addAttribute("operatingAirlines", null);
        modelMap.addAttribute("departureCity", null);
        modelMap.addAttribute("arrivalCity", null);
        return "admin/displayFlights";
    }

    @RequestMapping("admin/show-add")
    public String showAddFlight(){
        return "admin/addFlight";
    }

    @RequestMapping("admin/add-flight")
    public String addFlight(@ModelAttribute("flight") Flight flight, ModelMap modelMap){
        flightService.saveFlight(flight);
        String msg = "Added flight successfully with id: " + flight.getId();
        modelMap.addAttribute("msg", msg);
        return "admin/addFlight";
    }

    @RequestMapping("admin/show-edit")
    public String showEdit(@RequestParam("flightId") Long id, ModelMap modelMap) throws ParseException {
        Flight selectedFlight = flightService.findFlightById(id);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        modelMap.addAttribute("parsedEstimatedDepartureTime", dateFormat.format(selectedFlight.getEstimatedDepartureTime()));
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd-MM-yyyy");
        modelMap.addAttribute("parsedDepartureDate", dateTimeFormat.format(selectedFlight.getDepartureDate()));

        modelMap.addAttribute("flight", selectedFlight);
        return "admin/editFlight";
    }

    @RequestMapping("admin/edit-flight")
    public String editFlight(@ModelAttribute("flight") Flight flight, ModelMap modelMap){
        Flight editedFlight = flightService.editFlight(flight);
        String msg = "Flight successfully edited";
        modelMap.addAttribute("msg", msg);
        return "admin/editFlight";
    }

    @RequestMapping("admin/delete-flight")
    public String deleteFlight(@RequestParam("flightId") @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") Long id, ModelMap modelMap){
        Flight selectedFlight = flightService.findFlightById(id);
        flightService.deleteFlight(selectedFlight);
        List<Flight> flights = flightService.findAllFlights();
        modelMap.addAttribute("flights", flights);
        return "admin/displayFlights";
    }

    @RequestMapping("admin/search-flight-by-params")
    public String searchFlight(@RequestParam("flightNumber") String flightNumber,
                                     @RequestParam("operatingAirlines") String operatingAirlines,
                                     @RequestParam("departureCity") String departureCity,
                                     @RequestParam("arrivalCity") String arrivalCity,
                                     ModelMap modelMap){
        List<Flight> flights = flightService.findFlightsWithParams(flightNumber, operatingAirlines, departureCity,
                arrivalCity);
        modelMap.addAttribute("flights", flights);
        modelMap.addAttribute("flightNumber", flightNumber);
        modelMap.addAttribute("operatingAirlines", operatingAirlines);
        modelMap.addAttribute("departureCity", departureCity);
        modelMap.addAttribute("arrivalCity", arrivalCity);
        return "admin/displayFlights";
    }
}
