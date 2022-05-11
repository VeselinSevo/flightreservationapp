package com.codeinsight.flightreservation.flightreservation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @RequestMapping("/registration")
    public String showRegistrationPage() {
        return "registerUser";
    }
}
