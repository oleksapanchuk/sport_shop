package dev.oleksa.sportshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductItemDto {
    private Long id;
    private Long productId;
    private Long sizeId;
    private Long colorId;
    private String sku;
    private Integer quantity_in_stock;
    private String imageUrl;
    private Integer price;
}
