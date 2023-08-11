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
public class ProductItem {
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @NotNull
    private Product product;

    @ManyToOne
    @JoinColumn(name = "size_id")
    @NotNull
    private ProductSize size;

    @ManyToOne
    @JoinColumn(name = "color_id")
    @NotNull
    private ProductColor color;

    @Column(length = 100)
    @NotNull
    private String sku;

    @NotNull
    private Integer quantity_in_stock;

    @Column(length = 500)
    @NotNull
    private String imageUrl;

    @NotNull
    private Integer price;

}
