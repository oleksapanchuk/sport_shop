package dev.oleksa.sportshop.model.user.address;

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
    @Column(length = 10)
    private String unitNumber;
    @NotNull
    @Column(length = 10)
    private String streetNumber;
    @NotNull
    @Column(length = 150)
    private String address;
    @NotNull
    @Column(length = 100)
    private String city;
    @ManyToOne
    @JoinColumn(name = "region_id")
    @NotNull
    private Region region;
    @NotNull
    @Column(length = 10)
    private String postalCode;

}
