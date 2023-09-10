package dev.oleksa.sportshop.service;

import dev.oleksa.sportshop.model.dto.DiscountDto;

public interface DiscountService {
    DiscountDto createDiscount(DiscountDto discountDto);

    DiscountDto readDiscount(Long discountId);

    DiscountDto updateDiscount(DiscountDto discountDto);

    Boolean deleteDiscount(Long discountId);
}
