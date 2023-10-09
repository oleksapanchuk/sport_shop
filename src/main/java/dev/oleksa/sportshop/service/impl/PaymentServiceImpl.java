package dev.oleksa.sportshop.service.impl;

import dev.oleksa.sportshop.mapper.PaymentMapper;
import dev.oleksa.sportshop.dto.payment.PaymentDto;
import dev.oleksa.sportshop.model.payment.PaymentMethod;
import dev.oleksa.sportshop.repository.PaymentRepository;
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
    private final PaymentMapper paymentMapper;

    @Override
    public PaymentDto createPayment(PaymentDto paymentDto) {
        PaymentMethod payment = paymentMapper.toEntity(paymentDto);
        PaymentMethod createdPayment = paymentRepository.save(payment);
        return paymentMapper.toDto(createdPayment);
    }

    @Override
    public PaymentDto readPayment(Long id) {
        return paymentMapper.toDto(
                paymentRepository.findById(id)
                .orElseThrow()
        );
    }

    @Override
    public PaymentDto updatePayment(PaymentDto payment, Long id) {
        PaymentMethod paymentMethod = paymentMapper.toEntity(payment);
        return paymentMapper.toDto(paymentRepository.save(paymentMethod));
    }

    @Override
    public Boolean deletePayment(Long id) {
        paymentRepository.deleteById(id);
        return true;
    }
}