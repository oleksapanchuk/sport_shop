package dev.oleksa.sportshop.controller;

import dev.oleksa.sportshop.dto.PaymentDto;
import dev.oleksa.sportshop.model.payment.PaymentMethod;
import dev.oleksa.sportshop.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PaymentMethodController {

    private final PaymentService paymentService;

    @GetMapping("/user/{userId}/payment-methods")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<List<PaymentMethod>> getPaymentMethodsForUser(
            @PathVariable Long userId
    ) {

        return ResponseEntity.ok().body(paymentService.getAllPaymentMethods(userId));
    }

    @GetMapping("/user/{userId}/payment-method/{paymentMethodId}")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<PaymentMethod> getPaymentMethod(
            @PathVariable Long userId,
            @PathVariable Long paymentMethodId
    ) {

        return ResponseEntity.ok().body(paymentService.readPayment(paymentMethodId));
    }

    @PostMapping("/user/{userId}/payment-method")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<PaymentMethod> createPaymentMethod(
            @PathVariable Long userId,
            @RequestBody PaymentDto paymentDto
    ) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(String.format("/api/user/{%d}/payment-method", userId)).toUriString());

        return ResponseEntity.created(uri).body(paymentService.createPayment(paymentDto));
    }

    @PutMapping("/user/{userId}/payment-method/{paymentMethodId}")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<PaymentMethod> updatePaymentMethod(
            @PathVariable Long userId,
            @PathVariable Long paymentMethodId,
            @RequestBody PaymentDto paymentDto
    ) {

        return ResponseEntity.accepted().body(paymentService.updatePayment(paymentMethodId, paymentDto));
    }

    @DeleteMapping("/user/{userId}/payment-method/{paymentMethodId}")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<Boolean> deletePaymentMethod(
            @PathVariable Long userId,
            @PathVariable Long paymentMethodId
    ) {

        return ResponseEntity.accepted().body(paymentService.deletePayment(paymentMethodId));
    }

}
