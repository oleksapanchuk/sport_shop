package dev.oleksa.sportshop.repository;

import dev.oleksa.sportshop.model.payment.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentMethod, Long> {
    Optional<PaymentMethod> findById(Long id);
}
