package dev.oleksa.sportshop.model.dto;

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
    private String methodNameUa;
    private String methodNameEng;
    private Integer price;
}
