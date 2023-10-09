package dev.oleksa.sportshop.dto;

import dev.oleksa.sportshop.model.order.ShippingMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto {
    private Long id;
    private Long userId;
    private Long paymentMethodId;
    private Long shippingAddressId;
    private Long shippingMethodId;
    private Integer total;
    private Long orderStatusId;
    private Set<Long> productIds;
}
