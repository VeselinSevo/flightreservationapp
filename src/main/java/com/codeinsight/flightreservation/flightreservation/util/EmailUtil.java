package com.codeinsight.flightreservation.flightreservation.util;

import com.codeinsight.flightreservation.flightreservation.entities.Flight;
import com.codeinsight.flightreservation.flightreservation.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailUtil {

    @Value("${com.codeinsight.flightreservation.itinerary.email.subject}")
    private String ITINERARY_EMAIL_SUBJECT;
    @Value("${com.codeinsight.flightreservation.itinerary.email.body}")
    private String ITINERARY_EMAIL_BODY;
    @Value("${com.codeinsight.flightreservation.registration.email.subject}")
    private String REGISTRATION_EMAIL_SUBJECT;

    @Autowired
    private JavaMailSender mailSender;


    public void sendItinerary(String to, String filePath){

        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(ITINERARY_EMAIL_SUBJECT);
            helper.setText(ITINERARY_EMAIL_BODY);
            helper.addAttachment("itinerary.pdf", new File(filePath));
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendRegisterToEmail(String to, User user) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setSubject(REGISTRATION_EMAIL_SUBJECT);
            helper.setTo(to);
            helper.setText("Hello " + user.getFirstName() + ", you have successfully registered to Flight Reservation application");
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    public void sendNewFlightAddedToEmail(String to, Flight flight) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setSubject("New Flight Added");
            helper.setTo(to);
            helper.setText("New Flight Added: \n" + "From: "+ flight.getDepartureCity() + " To: " + flight.getArrivalCity());
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
