package dev.oleksa.sportshop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {
    private Long id;
    private Long categoryId;
    private Long discountId;
    private Long brandId;
    private String nameUa;
    private String nameEng;
    private String descriptionUa;
    private String descriptionEng;
    private String imageUrl;
}
