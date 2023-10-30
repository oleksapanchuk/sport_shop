package dev.oleksa.sportshop.model.payment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.oleksa.sportshop.model.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.AUTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private UserEntity user;

    @NotNull
    @Column(name = "account_number", nullable = false, length = 16)
    private String accountNumber;

    @NotNull
    @Column(name = "provider", nullable = false, length = 70)
    private String provider;

    @NotNull
    @Column(name = "expiry_date", nullable = false, length = 5)
    private String expiryDate;

    @NotNull
    @Column(name = "is_default", nullable = false)
    private Boolean isDefault = false;
}
