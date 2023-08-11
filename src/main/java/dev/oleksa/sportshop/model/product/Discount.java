package dev.oleksa.sportshop.model.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.AUTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Discount {
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull
    private String nameUa;

    @NotNull
    private String nameEng;

    @Column(length = 3000)
    private String descriptionUa;

    @Column(length = 3000)
    private String descriptionEng;

    @NotNull
    private Integer discountPercent;

}
