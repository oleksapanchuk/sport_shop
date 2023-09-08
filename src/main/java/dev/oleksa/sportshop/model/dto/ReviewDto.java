package dev.oleksa.sportshop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReviewDto {
    private String comment;
    private Integer ratingValue;
    private Long productItemId;
    private Long userId;
}
