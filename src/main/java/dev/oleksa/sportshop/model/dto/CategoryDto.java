package dev.oleksa.sportshop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDto {
    private Long parentCategoryId;
    private String nameUa;
    private String nameEng;
    private String imageUrl;
}
