package dev.oleksa.sportshop.service.impl;

import dev.oleksa.sportshop.mapper.AddressMapper;
import dev.oleksa.sportshop.model.dto.AddressDto;
import dev.oleksa.sportshop.repository.AddressRepository;
import dev.oleksa.sportshop.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Override
    public AddressDto createAddress(AddressDto addressDto) {
        var address = addressMapper.toEntity(addressDto);

        address = addressRepository.save(address);

        return addressMapper.toDto(address);
    }

    @Override
    public AddressDto readAddress(Long addressId) {
        return addressMapper.toDto(
                addressRepository.findById(addressId)
                        .orElseThrow()
        );
    }

    @Override
    public AddressDto updateAddress(AddressDto addressDto) {
        var address = addressMapper.toEntity(addressDto);

        address = addressRepository.save(address);

        return addressMapper.toDto(address);
    }

    @Override
    public Boolean deleteAddress(Long addressId) {
        try {
            addressRepository.deleteById(addressId);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
