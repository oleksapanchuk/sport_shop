package dev.oleksa.sportshop.repository;

import dev.oleksa.sportshop.model.order.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<UserOrder, Long> {
    Optional<UserOrder> findById(Long aLong);
}