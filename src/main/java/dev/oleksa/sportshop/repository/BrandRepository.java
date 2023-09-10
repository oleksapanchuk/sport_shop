package dev.oleksa.sportshop.repository;

import dev.oleksa.sportshop.model.product.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Brand> findById(Long aLong);
}
