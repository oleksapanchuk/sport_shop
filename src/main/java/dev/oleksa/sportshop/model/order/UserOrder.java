package dev.oleksa.sportshop.model.order;

import dev.oleksa.sportshop.model.payment.PaymentMethod;
import dev.oleksa.sportshop.model.product.ProductItem;
import dev.oleksa.sportshop.model.user.UserAccount;
import dev.oleksa.sportshop.model.user.address.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.AUTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class UserOrder {
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private UserAccount user;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "payment_method_id")
    @NotNull
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "shipping_address_id")
    @NotNull
    private Address shippingAddress;

    @ManyToOne
    @JoinColumn(name = "shipping_method_id")
    @NotNull
    private ShippingMethod shippingMethod;

    private Integer total;

    @ManyToOne
    @JoinColumn(name = "order_status_id")
    @NotNull
    private OrderStatus orderStatus;

    @ManyToMany
    @JoinTable(
            name = "order_has_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "produt_item_id")
    )
    private Set<ProductItem> products = new HashSet<>();
}
