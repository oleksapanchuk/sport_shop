package dev.oleksa.sportshop.service.impl;

import dev.oleksa.sportshop.dto.request.AddressRequest;
import dev.oleksa.sportshop.exception.NotFoundException;
import dev.oleksa.sportshop.mapper.AddressMapper;
import dev.oleksa.sportshop.model.user.address.Address;
import dev.oleksa.sportshop.repository.AddressRepository;
import dev.oleksa.sportshop.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Override
    @Transactional
    public Address readAddress(Long addressId) throws NotFoundException {
        return addressRepository.findById(addressId)
                .orElseThrow(() -> new NotFoundException("Address with id " + addressId + " not found"));
    }

    @Override
    public List<Address> readAddressesForUser(Long userId) throws NotFoundException {
        return addressRepository.findAllByUserId(userId);
    }

    @Override
    public Address createAddress(AddressRequest addressRequest) {
        Address address = addressMapper.toEntity(addressRequest).orElseThrow();

        address = addressRepository.save(address);

        return address;
    }

    @Override
    public Address updateAddress(Long addressId, AddressRequest addressRequest) {
        Address address = addressMapper.toEntity(addressRequest).orElseThrow();
        address.setId(addressId);

        address = addressRepository.save(address);

        return address;
    }

    @Override
    public Boolean deleteAddress(Long addressId) {
        try {
            log.info("Address id to delete: " + addressId);
            addressRepository.deleteById(addressId);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
