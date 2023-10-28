package dev.oleksa.sportshop.repository;

import dev.oleksa.sportshop.model.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findById(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE UserEntity u SET u.firstName = :firstName, u.lastName = :lastName, u.phone = :phone, u.dateOfBirth = :dateOfBirth WHERE u.id = :id")
    int updateUserInfo(
            @Param("id") Long id,
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("phone") String phone,
            @Param("dateOfBirth") Date dateOfBirth
    );

    @Modifying
    @Transactional
    @Query("UPDATE UserEntity u SET u.gender.id = :genderId WHERE u.id = :id")
    int updateUserGender(@Param("id") Long id, @Param("genderId") Long genderId);

    @Modifying
    @Transactional
    @Query("UPDATE UserEntity u SET u.imageUrl= :imageUrl WHERE u.id = :id")
    int updateUserAvatar(@Param("id") Long id, @Param("imageUrl") String imageUrl);

}
