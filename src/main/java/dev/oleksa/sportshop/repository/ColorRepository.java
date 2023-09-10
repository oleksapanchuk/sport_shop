package dev.oleksa.sportshop.repository;

import dev.oleksa.sportshop.model.product.ProductColor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ColorRepository extends JpaRepository<ProductColor, Long> {
    Optional<ProductColor> findById(Long aLong);
}
