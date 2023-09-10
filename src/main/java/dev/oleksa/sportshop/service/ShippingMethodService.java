package dev.oleksa.sportshop.service;

import dev.oleksa.sportshop.model.dto.ShippingMethodDto;

public interface ShippingMethodService {
    ShippingMethodDto createShippingMethod(ShippingMethodDto shippingMethodDto);

    ShippingMethodDto readShippingMethod(Long shippingMethod);

    ShippingMethodDto updateShippingMethod(ShippingMethodDto shippingMethodDto);

    Boolean deleteShippingMethod(Long shippingMethod);
}
