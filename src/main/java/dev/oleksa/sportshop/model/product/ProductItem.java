package dev.oleksa.sportshop.model.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class ProductItem {
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "color_id", nullable = false)
    private ProductColor color;

    @NotNull
    @Column(name = "image_url", nullable = false, length = 1000)
    private String imageUrl;

}
