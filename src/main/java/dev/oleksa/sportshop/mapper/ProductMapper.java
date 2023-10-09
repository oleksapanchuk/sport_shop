package dev.oleksa.sportshop.mapper;

import dev.oleksa.sportshop.dto.ProductDto;
import dev.oleksa.sportshop.exception.NotFoundException;
import dev.oleksa.sportshop.model.product.Product;
import dev.oleksa.sportshop.repository.BrandRepository;
import dev.oleksa.sportshop.repository.CategoryRepository;
import dev.oleksa.sportshop.repository.DiscountRepository;
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
public class ProductMapper {

    private final ModelMapper mapper;
    private final DiscountRepository discountRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;

    public Product toEntity(ProductDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Product.class);
    }

    public ProductDto toDto(Product entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, ProductDto.class);
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Product.class, ProductDto.class)
                .addMappings(m -> m.skip(ProductDto::setDiscountId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(ProductDto::setCategoryId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(ProductDto::setBrandId)).setPostConverter(toDtoConverter())
        ;
        mapper.createTypeMap(ProductDto.class, Product.class)
                .addMappings(m -> m.skip(Product::setCategory)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(Product::setBrand)).setPostConverter(toEntityConverter())
        ;
    }

    public Converter<Product, ProductDto> toDtoConverter() {
        return context -> {
            Product source = context.getSource();
            ProductDto destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    public Converter<ProductDto, Product> toEntityConverter() {
        return context -> {
            ProductDto source = context.getSource();
            Product destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    public void mapSpecificFields(ProductDto source, Product destination) {
        try {
            destination.setCategory(
                    Objects.isNull(source) || Objects.isNull(source.getCategoryId())
                            ? null
                            : categoryRepository.findById(source.getCategoryId())
                            .orElseThrow(() -> new NotFoundException("Category not found"))
            );

            destination.setBrand(
                    Objects.isNull(source) || Objects.isNull(source.getBrandId())
                            ? null
                            : brandRepository.findById(source.getBrandId())
                            .orElseThrow(() -> new NotFoundException("Brand not found"))
            );
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void mapSpecificFields(Product source, ProductDto destination) {
        destination.setCategoryId(
                Objects.isNull(source) || Objects.isNull(source.getId())
                        ? null
                        : source.getCategory().getId()
        );

        destination.setBrandId(
                Objects.isNull(source) || Objects.isNull(source.getId())
                        ? null
                        : source.getBrand().getId()
        );
    }
}