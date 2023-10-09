package dev.oleksa.sportshop.service;

import dev.oleksa.sportshop.dto.payment.PaymentDto;

public interface PaymentService {
    PaymentDto createPayment(PaymentDto payment);

    PaymentDto readPayment(Long id);

    PaymentDto updatePayment(PaymentDto payment, Long id);

    Boolean deletePayment(Long id);
}
