package dev.oleksa.sportshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sport-shop")
public class DemoController {

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
}
