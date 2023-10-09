package dev.oleksa.sportshop.model.user.address;

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
public class Address {
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @NotNull
    @Column(name = "unit_number", nullable = false, length = 7)
    private String unitNumber;

    @NotNull
    @Column(name = "street_number", nullable = false, length = 7)
    private String streetNumber;

    @NotNull
    @Column(name = "address_line", nullable = false, length = 200)
    private String addressLine;

    @NotNull
    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @NotNull
    @Column(name = "postal_code", nullable = false, length = 10)
    private String postalCode;

    @NotNull
    @Column(name = "is_default", nullable = false)
    private Boolean isDefault = false;

}
