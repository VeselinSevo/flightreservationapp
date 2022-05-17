package com.codeinsight.flightreservation.flightreservation.util;

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

}
