package dev.oleksa.sportshop.service.impl;

import dev.oleksa.sportshop.model.dto.PaymentDto;
import dev.oleksa.sportshop.model.payment.PaymentMethod;
import dev.oleksa.sportshop.repository.PaymentRepository;
import dev.oleksa.sportshop.repository.UserRepository;
import dev.oleksa.sportshop.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;

    @Override
    public PaymentMethod createPayment(PaymentDto payment) {
        return paymentRepository.save(PaymentMethod.builder()
                .user(userRepository.findById(payment.getUserId()).orElseThrow())
                .accountNumber(payment.getAccountNumber())
                .expiryDate(payment.getExpiryDate())
                .isDefault(payment.getIsDefault())
                .provider(payment.getProvider())
                .build());
    }

    @Override
    public PaymentMethod readPayment(Long id) {
        return paymentRepository.findById(id).orElseThrow();
    }

    @Override
    public PaymentMethod updatePayment(PaymentDto payment, Long id) {
        PaymentMethod paymentMethod = this.readPayment(id);
        paymentMethod.setAccountNumber(payment.getAccountNumber());
        paymentMethod.setExpiryDate(payment.getExpiryDate());
        paymentMethod.setIsDefault(payment.getIsDefault());
        paymentMethod.setProvider(payment.getProvider());
        return paymentRepository.save(paymentMethod);
    }

    @Override
    public Boolean deletePayment(Long id) {
        paymentRepository.deleteById(id);
        return true;
    }
}
