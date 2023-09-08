package dev.oleksa.sportshop.controller;

import dev.oleksa.sportshop.model.dto.PaymentDto;
import dev.oleksa.sportshop.model.payment.PaymentMethod;
import dev.oleksa.sportshop.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/sport-shop/user/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentMethod> getPaymentById(@PathVariable Long paymentId) {
        return ResponseEntity.ok(paymentService.readPayment(paymentId));
    }

    @PostMapping("/save")
    public ResponseEntity<PaymentMethod> savePayment(@RequestBody PaymentDto payment) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/sport-shop/user/payment/save").toUriString());
        return ResponseEntity.created(uri).body(paymentService.createPayment(payment));
    }

    @PutMapping("/update/{paymentId}")
    public ResponseEntity<PaymentMethod> updatePayment(
            @PathVariable Long paymentId,
            @RequestBody PaymentDto payment)
    {
        return ResponseEntity.ok(paymentService.updatePayment(payment, paymentId));
    }

    @DeleteMapping("/delete/{paymentId}")
    public ResponseEntity<String> deletePayment(@PathVariable Long paymentId) {
        if (paymentService.deletePayment(paymentId)) return ResponseEntity.ok("Payment successfully deleted");
        return ResponseEntity.ok("NOT"); // todo
    }

}
