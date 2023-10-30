package dev.oleksa.sportshop.service.impl;

import dev.oleksa.sportshop.dto.PaymentDto;
import dev.oleksa.sportshop.mapper.PaymentMapper;
import dev.oleksa.sportshop.model.payment.PaymentMethod;
import dev.oleksa.sportshop.repository.PaymentRepository;
import dev.oleksa.sportshop.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public List<PaymentMethod> getAllPaymentMethods(Long userId) {
        return paymentRepository.findAllByUserId(userId);
    }

    @Override
    public PaymentMethod createPayment(PaymentDto paymentDto) {
        PaymentMethod payment = paymentMapper.toEntity(paymentDto);
        return paymentRepository.save(payment);
    }

    @Override
    public PaymentMethod readPayment(Long paymentId) {
        return paymentRepository.findById(paymentId)
                .orElseThrow();
    }

    @Override
    public PaymentMethod updatePayment(Long paymentId, PaymentDto payment) {
        PaymentMethod paymentMethod = paymentMapper.toEntity(payment);
        paymentMethod.setId(paymentId);
        return paymentRepository.save(paymentMethod);
    }

    @Override
    public Boolean deletePayment(Long paymentId) {
        try {
            paymentRepository.deleteById(paymentId);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

}