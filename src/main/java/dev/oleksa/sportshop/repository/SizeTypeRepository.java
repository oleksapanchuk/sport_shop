package dev.oleksa.sportshop.repository;

import dev.oleksa.sportshop.model.product.ProductSizeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeTypeRepository extends JpaRepository<ProductSizeType, Long> {
}
