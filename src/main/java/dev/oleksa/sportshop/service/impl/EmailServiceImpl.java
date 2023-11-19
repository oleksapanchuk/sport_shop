package dev.oleksa.sportshop.service.impl;

import dev.oleksa.sportshop.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
//    @Value("${spring.mail.host}")
    private String host = "smtp.gmail.com";
//    @Value("${spring.mail.email}")
    private String from = "olexadrpan4uk@gmail.com";
    private final JavaMailSender emailSender;

    @Override
    public void sendConfirmationEmail(String to, String token) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject("Confirm your account");
            message.setFrom(from);
            message.setTo(to);
            message.setText("Confirm your account by clicking on the link below: \n" +
                    "http://localhost:8080/sport-shop/auth/confirm-account?token=" + token);
            emailSender.send(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject("Some Subject");
            message.setFrom(from);
            message.setTo(to);
            message.setText("Hello World \n Spring Boot Email");
            emailSender.send(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
