package dev.oleksa.sportshop.model.order;

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
@Table(name = "order_has_products")
public class OrderProduct {
    @EmbeddedId
    private OrderProductKey id;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

}
