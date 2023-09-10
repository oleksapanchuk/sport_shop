package dev.oleksa.sportshop.repository;

import dev.oleksa.sportshop.model.user.address.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    Optional<Region> findById(Long aLong);
}
