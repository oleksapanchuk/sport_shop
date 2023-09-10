package dev.oleksa.sportshop.mapper;

import dev.oleksa.sportshop.model.dto.DiscountDto;
import dev.oleksa.sportshop.model.product.Discount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class DiscountMapper {

    private final ModelMapper mapper;

    public Discount toEntity(DiscountDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Discount.class);
    }

    public DiscountDto toDto(Discount entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, DiscountDto.class);
    }
}
