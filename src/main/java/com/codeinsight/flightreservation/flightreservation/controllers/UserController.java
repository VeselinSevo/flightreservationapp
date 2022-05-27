package com.codeinsight.flightreservation.flightreservation.controllers;

import com.codeinsight.flightreservation.flightreservation.entities.User;
import com.codeinsight.flightreservation.flightreservation.services.SecurityService;
import com.codeinsight.flightreservation.flightreservation.services.UserService;
import com.codeinsight.flightreservation.flightreservation.util.EmailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private BCryptPasswordEncoder encoder;

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
        user.setPassword(encoder.encode(user.getPassword()));
        if(user.getSubscribed()==null){             //Pitati Vukasina
            user.setSubscribed("not subscribed");
        }
        LOGGER.info("Inside registerUser(), USER: " + user);
        userService.saveUser(user);
        emailUtil.sendRegisterToEmail(user.getEmail(), user);
        LOGGER.info("Sending registered successfully massage to email: ");
        return "login/login";
    }
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, ModelMap modelMap){
        LOGGER.info("Inside loginUser(), EMAIL: " + email);
        boolean loginResponse = securityService.login(email, password);
        if(loginResponse){
            return "flights/findFlights";
        }else{
            modelMap.addAttribute("error", "Invalid Username or Password. Please try again.");
            return "login/login";
        }
    }
}
