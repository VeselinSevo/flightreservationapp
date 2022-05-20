package com.codeinsight.flightreservation.flightreservation.util;

import com.codeinsight.flightreservation.flightreservation.entities.Passenger;
import com.codeinsight.flightreservation.flightreservation.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailUtil {

    @Autowired
    private JavaMailSender mailSender;


    public void sendItinerary(String to, String filePath){

        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject("Flight Reservation Itinerary");
            helper.setText("Please find attached your flight itinerary");
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
            helper.setSubject("Flight Reservation - Registered successfully");
            helper.setTo(to);
            helper.setText("Hello " + user.getFirstName() + ", you have successfully registered to Flight Reservation application");
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
