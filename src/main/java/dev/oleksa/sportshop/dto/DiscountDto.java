package dev.oleksa.sportshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DiscountDto {
    private Long id;
    private String nameUa;
    private String nameEng;
    private String descriptionUa;
    private String descriptionEng;
    private Integer discountPercent;
}
