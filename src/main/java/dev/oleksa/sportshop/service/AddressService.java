package dev.oleksa.sportshop.service;

import dev.oleksa.sportshop.dto.request.AddressRequest;
import dev.oleksa.sportshop.exception.NotFoundException;
import dev.oleksa.sportshop.model.user.address.Address;

import java.util.List;

public interface AddressService {
    Address readAddress(Long addressId) throws NotFoundException;
    List<Address> readAddressesForUser(Long userId) throws NotFoundException;
    Address createAddress(AddressRequest addressRequest);
    Address updateAddress(Long addressId, AddressRequest addressRequest);
    Boolean deleteAddress(Long addressId);
}
