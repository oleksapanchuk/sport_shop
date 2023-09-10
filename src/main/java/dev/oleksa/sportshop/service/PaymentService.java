package dev.oleksa.sportshop.service;

import dev.oleksa.sportshop.model.dto.PaymentDto;
import dev.oleksa.sportshop.model.payment.PaymentMethod;

public interface PaymentService {
    PaymentDto createPayment(PaymentDto payment);
    PaymentDto readPayment(Long id);
    PaymentDto updatePayment(PaymentDto payment, Long id);
    Boolean deletePayment(Long id);
}
