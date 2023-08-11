package dev.oleksa.sportshop.model.payment;

import dev.oleksa.sportshop.model.user.UserAccount;
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
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private UserAccount user;
    @NotNull
    @Column(length = 100)
    private String provider;
    @NotNull
    @Column(length = 16)
    private String accountNumber;
    @NotNull
    @Column(length = 5)
    private String expiryDate;
    private Boolean isDefault = false;
}
