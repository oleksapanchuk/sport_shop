package dev.oleksa.sportshop.controller;

import dev.oleksa.sportshop.security.JwtService;
import dev.oleksa.sportshop.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class EmailController {

    private final EmailService emailService;
    private final JwtService jwtService;

    @GetMapping("/send-email-confirmation")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public void sendEmailConfirmation(@RequestParam String email) {

        String token = jwtService.generateToken(email);

        emailService.sendConfirmationEmail(email, token);

        log.info("Email confirmation sent!");
    }
}
