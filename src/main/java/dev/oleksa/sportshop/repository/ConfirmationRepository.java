package dev.oleksa.sportshop.repository;

import dev.oleksa.sportshop.model.user.Confirmation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationRepository extends JpaRepository<Confirmation, Long> {
    Optional<Confirmation> findById(Long aLong);
}
