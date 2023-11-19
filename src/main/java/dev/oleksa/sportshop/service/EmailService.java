package dev.oleksa.sportshop.service;

public interface EmailService {
    void sendConfirmationEmail(String to, String token);
    void sendSimpleMessage(String to, String subject, String text);
}
