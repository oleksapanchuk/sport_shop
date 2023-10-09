package dev.oleksa.sportshop.service.impl;

import dev.oleksa.sportshop.mapper.ShippingMethodMapper;
import dev.oleksa.sportshop.dto.ShippingMethodDto;
import dev.oleksa.sportshop.repository.ShippingMethodRepository;
import dev.oleksa.sportshop.service.ShippingMethodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ShippingMethodServiceImpl implements ShippingMethodService {

    private final ShippingMethodRepository shippingMethodRepository;
    private final ShippingMethodMapper shippingMethodMapper;

    @Override
    public ShippingMethodDto createShippingMethod(ShippingMethodDto shippingMethodDto) {
        var shippingMethod = shippingMethodMapper.toEntity(shippingMethodDto);

        shippingMethod = shippingMethodRepository.save(shippingMethod);

        return shippingMethodMapper.toDto(shippingMethod);
    }

    @Override
    public ShippingMethodDto readShippingMethod(Long shippingMethod) {
        return shippingMethodMapper.toDto(
                shippingMethodRepository.findById(shippingMethod)
                        .orElseThrow()
        );
    }

    @Override
    public ShippingMethodDto updateShippingMethod(ShippingMethodDto shippingMethodDto) {
        var shippingMethod = shippingMethodMapper.toEntity(shippingMethodDto);

        shippingMethod = shippingMethodRepository.save(shippingMethod);

        return shippingMethodMapper.toDto(shippingMethod);
    }

    @Override
    public Boolean deleteShippingMethod(Long shippingMethod) {
        try {
            shippingMethodRepository.deleteById(shippingMethod);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
