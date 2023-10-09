package dev.oleksa.sportshop.service;

import dev.oleksa.sportshop.dto.AddressDto;

public interface AddressService {
    AddressDto createAddress(AddressDto addressDto);

    AddressDto readAddress(Long addressId);

    AddressDto updateAddress(AddressDto addressDto);

    Boolean deleteAddress(Long addressId);
}
