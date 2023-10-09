package dev.oleksa.sportshop.mapper;

import dev.oleksa.sportshop.dto.SizeDto;
import dev.oleksa.sportshop.model.product.ProductSize;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class SizeMapper {

    private final ModelMapper mapper;

    public ProductSize toEntity(SizeDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, ProductSize.class);
    }

    public SizeDto toDto(ProductSize entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, SizeDto.class);
    }

}
