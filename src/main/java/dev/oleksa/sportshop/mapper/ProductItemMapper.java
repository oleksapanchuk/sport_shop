package dev.oleksa.sportshop.mapper;

import dev.oleksa.sportshop.exception.NotFoundException;
import dev.oleksa.sportshop.model.dto.ProductItemDto;
import dev.oleksa.sportshop.model.product.ProductItem;
import dev.oleksa.sportshop.repository.ColorRepository;
import dev.oleksa.sportshop.repository.ProductRepository;
import dev.oleksa.sportshop.repository.SizeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductItemMapper {

    private final ModelMapper mapper;
    private final ProductRepository productRepository;
    private final SizeRepository sizeRepository;
    private final ColorRepository colorRepository;


    public ProductItem toEntity(ProductItemDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, ProductItem.class);
    }

    public ProductItemDto toDto(ProductItem entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, ProductItemDto.class);
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(ProductItem.class, ProductItemDto.class)
                .addMappings(m -> m.skip(ProductItemDto::setProductId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(ProductItemDto::setSizeId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(ProductItemDto::setColorId)).setPostConverter(toDtoConverter())
        ;
        mapper.createTypeMap(ProductItemDto.class, ProductItem.class)
                .addMappings(m -> m.skip(ProductItem::setProduct)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(ProductItem::setSize)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(ProductItem::setColor)).setPostConverter(toEntityConverter())
        ;
    }

    public Converter<ProductItem, ProductItemDto> toDtoConverter() {
        return context -> {
            ProductItem source = context.getSource();
            ProductItemDto destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    public Converter<ProductItemDto, ProductItem> toEntityConverter() {
        return context -> {
            ProductItemDto source = context.getSource();
            ProductItem destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    public void mapSpecificFields(ProductItemDto source, ProductItem destination) {
        try {
            destination.setProduct(
                    Objects.isNull(source) || Objects.isNull(source.getProductId())
                            ? null
                            : productRepository.findById(source.getProductId())
                            .orElseThrow(() -> new NotFoundException("Product not found"))
            );
            destination.setSize(
                    Objects.isNull(source) || Objects.isNull(source.getSizeId())
                            ? null
                            : sizeRepository.findById(source.getSizeId())
                            .orElseThrow(() -> new NotFoundException("Size not found"))
            );
            destination.setColor(
                    Objects.isNull(source) || Objects.isNull(source.getColorId())
                            ? null
                            : colorRepository.findById(source.getColorId())
                            .orElseThrow(() -> new NotFoundException("Color not found"))
            );
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void mapSpecificFields(ProductItem source, ProductItemDto destination) {
        destination.setProductId(
                Objects.isNull(source) || Objects.isNull(source.getId())
                        ? null
                        : source.getProduct().getId()
        );
        destination.setSizeId(
                Objects.isNull(source) || Objects.isNull(source.getId())
                        ? null
                        : source.getSize().getId()
        );
        destination.setColorId(
                Objects.isNull(source) || Objects.isNull(source.getId())
                        ? null
                        : source.getColor().getId()
        );
    }
}
