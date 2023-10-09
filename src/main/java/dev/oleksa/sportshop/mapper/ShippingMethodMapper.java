package dev.oleksa.sportshop.mapper;

import dev.oleksa.sportshop.dto.ShippingMethodDto;
import dev.oleksa.sportshop.model.order.ShippingMethod;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class ShippingMethodMapper {

    private final ModelMapper mapper;

    public ShippingMethod toEntity(ShippingMethodDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, ShippingMethod.class);
    }

    public ShippingMethodDto toDto(ShippingMethod entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, ShippingMethodDto.class);
    }
}
