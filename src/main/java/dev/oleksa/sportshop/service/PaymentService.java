package dev.oleksa.sportshop.service;

import dev.oleksa.sportshop.dto.PaymentDto;
import dev.oleksa.sportshop.model.payment.PaymentMethod;

import java.util.List;

public interface PaymentService {
    List<PaymentMethod> getAllPaymentMethods(Long userId);
    PaymentMethod createPayment(PaymentDto payment);
    PaymentMethod readPayment(Long paymentId);
    PaymentMethod updatePayment( Long paymentId, PaymentDto payment);
    Boolean deletePayment(Long paymentId);
}
