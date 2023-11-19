package dev.oleksa.sportshop.dto.response;

import dev.oleksa.sportshop.model.product.Discount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductResponse {
    private Long productId;
    private String categoryName;
    private String brandName;
    private String productName;
    private String description;
    private Long colorId;
    private List<Long> availableSizeIds;
    private List<Long> availableColorIds;
    private String imageUrl;
    private BigDecimal price;
    private Integer likesNumber;
    private BigDecimal rating;
    private List<Discount> discounts;
}
