package dev.oleksa.sportshop.model.product;

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
public class Product {
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotNull
    private Category category;

    @ManyToOne
    @JoinColumn(name = "discount_id")
    @NotNull
    private Discount discount;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    @NotNull
    private Brand brand;

    @NotNull
    private String nameUa;

    @NotNull
    private String nameEng;

    @Column(length = 3000)
    private String descriptionUa;

    @Column(length = 3000)
    private String descriptionEng;

    @Column(length = 300)
    @NotNull
    private String imageUrl;

}
