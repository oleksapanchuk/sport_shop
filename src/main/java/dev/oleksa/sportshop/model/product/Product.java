package dev.oleksa.sportshop.model.product;

import dev.oleksa.sportshop.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.GenerationType.AUTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@NamedNativeQuery(
        name = "Product.findProductById_Named",
        query = "SELECT p.id, p.name_ua, p.name_eng, p.category_id, p.description_ua, p.description_eng, p.image_url, p.brand_id, p.price, p.likes_number, p.rating FROM Product p WHERE p.id = :id",
        resultSetMapping = "Mapping.ProductDto"
)
@SqlResultSetMapping(
        name = "Mapping.ProductDto",
        classes = @ConstructorResult(
                targetClass = ProductDto.class,
                columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "name_ua"),
                        @ColumnResult(name = "name_eng"),
                        @ColumnResult(name = "category_id", type = Long.class),
                        @ColumnResult(name = "description_ua"),
                        @ColumnResult(name = "description_eng"),
                        @ColumnResult(name = "image_url"),
                        @ColumnResult(name = "brand_id", type = Long.class),
                        @ColumnResult(name = "price", type = BigDecimal.class),
                        @ColumnResult(name = "likes_number", type = Integer.class),
                        @ColumnResult(name = "rating", type = BigDecimal.class)
                }
        )
)
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "name_ua", nullable = false)
    private String nameUa;

    @NotNull
    @Column(name = "name_eng", nullable = false)
    private String nameEng;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @NotNull
    @Column(name = "description_ua", nullable = false, length = 5000)
    private String descriptionUa;

    @NotNull
    @Column(name = "description_eng", nullable = false, length = 5000)
    private String descriptionEng;

    @NotNull
    @Column(name = "image_url", nullable = false, length = 1000)
    private String imageUrl;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @NotNull
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @NotNull
    @Column(name = "likes_number", nullable = false)
    private Integer likesNumber;

    @NotNull
    @Column(name = "rating", nullable = false, precision = 3, scale = 1)
    private BigDecimal rating;

    @NotNull
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "product_has_discounts",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "discount_id", referencedColumnName = "id")
    )
    private Collection<Discount> discounts = new ArrayList<>();

}
