package dev.oleksa.sportshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {
    private Long id;
    private String nameUa;
    private String nameEng;
    private Long categoryId;
    private String descriptionUa;
    private String descriptionEng;
    private String imageUrl;
    private Long brandId;
    private BigDecimal price;
    private Integer likesNumber;
    private BigDecimal rating;
}
