package dev.oleksa.sportshop.controller;

import dev.oleksa.sportshop.config.LocaleResolverConfiguration;
import dev.oleksa.sportshop.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sport-shop")
public class DemoController {


    private final MessageSource messageSource;

    private final LocaleResolverConfiguration localeResolver;

    private final EmailService emailService;


    @GetMapping("/greeting")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public String greeting(HttpServletRequest request) {

        Locale locale = localeResolver.resolveLocale(request);
        System.out.println("locale - " + locale);
        return messageSource.getMessage("hello", null, locale);
    }

    @GetMapping("/greeting-with-header")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public String greeting(
            @RequestHeader(name = "Accept-Language", required = false) String locale
    ) {

        return messageSource.getMessage("hello", null, localeResolver.resolveLocale(locale));
    }

    @GetMapping("/demo-controller")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("AUTHENTICATED");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> sayHelloAdmin() {
        return ResponseEntity.ok("ADMIN");
    }

    @GetMapping("/manager")
    public ResponseEntity<String> sayHelloManager() {
        return ResponseEntity.ok("MANAGER");
    }

    @GetMapping("/customer")
    public ResponseEntity<String> sayHelloCustomer() {
        return ResponseEntity.ok("CUSTOMER");
    }

    @GetMapping("/send-email")
    public ResponseEntity<Boolean> sendSimpleMessage() {
        emailService.sendSimpleMessage("alexanderpanchuk@ukr.net", "Test", "Test");
        return ResponseEntity.ok(true);
    }
}

