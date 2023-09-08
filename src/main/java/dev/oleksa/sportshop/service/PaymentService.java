package dev.oleksa.sportshop.service;

import dev.oleksa.sportshop.model.dto.PaymentDto;
import dev.oleksa.sportshop.model.payment.PaymentMethod;

public interface PaymentService {
    PaymentMethod createPayment(PaymentDto payment);
    PaymentMethod readPayment(Long id);
    PaymentMethod updatePayment(PaymentDto payment, Long id);
    Boolean deletePayment(Long id);
}
