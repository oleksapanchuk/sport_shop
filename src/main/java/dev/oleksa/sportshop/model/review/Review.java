package dev.oleksa.sportshop.model.review;

import dev.oleksa.sportshop.model.product.ProductItem;
import dev.oleksa.sportshop.model.user.UserAccount;
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
public class Review {
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private UserAccount user;

    private Integer ratingValue;

    @Column(length = 755)
    @NotNull
    private String comment;

    @ManyToOne
    @JoinColumn(name = "product_item_id")
    @NotNull
    private ProductItem product;

}
