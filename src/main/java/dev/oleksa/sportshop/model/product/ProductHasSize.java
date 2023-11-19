package dev.oleksa.sportshop.model.product;

import dev.oleksa.sportshop.model.order.OrderProductKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "product_has_sizes")
public class ProductHasSize {
    @EmbeddedId
    private ProductSizeKey id;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}
