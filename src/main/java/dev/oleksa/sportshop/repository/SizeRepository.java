package dev.oleksa.sportshop.repository;

import dev.oleksa.sportshop.model.product.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SizeRepository extends JpaRepository<ProductSize, Long> {
    Optional<ProductSize> findById(Long aLong);
}
