package dev.oleksa.sportshop.controller;

import dev.oleksa.sportshop.dto.PaymentDto;
import dev.oleksa.sportshop.model.CustomResponse;
import dev.oleksa.sportshop.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

import static dev.oleksa.sportshop.constants.ResponseConstant.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PaymentMethodController {

    private final PaymentService paymentService;

    @GetMapping("/user/{userId}/payment-methods")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<CustomResponse> getPaymentMethodsForUser(
            @PathVariable Long userId
    ) {

        return ResponseEntity.ok().body(
                CustomResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .requestMethod(GET)
                        .statusCode(OK.value())
                        .message("All payment methods. User id: " + userId)
                        .data(Map.of("payment-methods", paymentService.getAllPaymentMethods(userId)))
                        .build()
        );
    }

    @GetMapping("/user/{userId}/payment-method/{paymentMethodId}")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<CustomResponse> getPaymentMethod(
            @PathVariable Long userId,
            @PathVariable Long paymentMethodId
    ) {

        return ResponseEntity.ok().body(
                CustomResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .requestMethod(GET)
                        .statusCode(OK.value())
                        .data(Map.of("payment-method", paymentService.readPayment(paymentMethodId)))
                        .build()
        );
    }

    @PostMapping("/user/{userId}/payment-method")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<CustomResponse> addPaymentMethod(
            @PathVariable Long userId,
            @RequestBody PaymentDto paymentDto
    ) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(String.format("/api/user/{%d}/payment-method", userId)).toUriString());

        return ResponseEntity.created(uri).body(
                CustomResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .path(uri.getPath())
                        .requestMethod(POST)
                        .statusCode(CREATED.value())
                        .message("Payment Method added")
                        .data(Map.of("payment-method", paymentService.createPayment(paymentDto)))
                        .build()
        );
    }

    @PutMapping("/user/{userId}/payment-method/{paymentMethodId}")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<CustomResponse> updatePaymentMethod(
            @PathVariable Long userId,
            @PathVariable Long paymentMethodId,
            @RequestBody PaymentDto paymentDto
    ) {

        return ResponseEntity.ok().body(
                CustomResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .requestMethod(PUT)
                        .statusCode(OK.value())
                        .message("Updated payment method id:" + paymentMethodId)
                        .data(Map.of("payment-method", paymentService.updatePayment(paymentMethodId, paymentDto)))
                        .build()
        );
    }

    @DeleteMapping("/user/{userId}/payment-method/{paymentMethodId}")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<CustomResponse> deletePaymentMethod(
            @PathVariable Long userId,
            @PathVariable Long paymentMethodId
    ) {

        return ResponseEntity.ok().body(
                CustomResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .requestMethod(DELETE)
                        .statusCode(OK.value())
                        .message("Payment Method id: " + paymentMethodId + " deleted")
                        .data(Map.of("is_deleted", paymentService.deletePayment(paymentMethodId)))
                        .build()
        );
    }

}
