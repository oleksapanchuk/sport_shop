package dev.oleksa.sportshop.repository;

import dev.oleksa.sportshop.model.product.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
    Optional<Discount> findById(Long discountId);
}
