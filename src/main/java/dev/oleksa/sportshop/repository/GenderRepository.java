package dev.oleksa.sportshop.repository;

import dev.oleksa.sportshop.model.user.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository extends JpaRepository<Gender, Long> {
    Gender findByNameEng(String nameEng);
}
