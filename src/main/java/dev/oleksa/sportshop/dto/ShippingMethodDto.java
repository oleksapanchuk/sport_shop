package dev.oleksa.sportshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShippingMethodDto {
    private Long id;
    private String nameUa;
    private String nameEng;
    private Integer price;
}
