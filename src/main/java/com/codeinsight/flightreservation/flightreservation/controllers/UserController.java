package com.codeinsight.flightreservation.flightreservation.controllers;

import com.codeinsight.flightreservation.flightreservation.entities.User;
import com.codeinsight.flightreservation.flightreservation.services.UserService;
import com.codeinsight.flightreservation.flightreservation.util.EmailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private UserService userService;

    @RequestMapping("show-register")
    public String showRegisterPage() {
        LOGGER.info("Inside showRegisterPage()");
        return "login/register";
    }
    @RequestMapping("show-login")
    public String showLoginPage() {
        LOGGER.info("Inside showLoginPage()");
        return "login/login";
    }
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("user") User user){
        LOGGER.info("Inside registerUser(), USER: " + user);
        LOGGER.info("Sending registered successfully massage to email: ");
        emailUtil.sendRegisterToEmail(user.getEmail(), user);
        userService.saveUser(user);
        return "login/login";
    }
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, ModelMap modelMap){
        LOGGER.info("Inside loginUser(), EMAIL: " + email);
        User user = userService.findUserByEmail(email);
        if(user != null && user.getPassword().equals(password)){
            return "flights/findFlights";
        }else{
            modelMap.addAttribute("error", "Invalid Username or Password. Please try again.");
            return "login/login";
        }
    }
}
