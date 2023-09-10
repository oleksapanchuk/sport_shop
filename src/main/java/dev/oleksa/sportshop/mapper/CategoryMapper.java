package dev.oleksa.sportshop.mapper;

import dev.oleksa.sportshop.exception.NotFoundException;
import dev.oleksa.sportshop.model.dto.CategoryDto;
import dev.oleksa.sportshop.model.product.Category;
import dev.oleksa.sportshop.repository.CategoryRepository;
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
public class CategoryMapper {

    private final ModelMapper mapper;
    private final CategoryRepository categoryRepository;

    public Category toEntity(CategoryDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Category.class);
    }

    public CategoryDto toDto(Category entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, CategoryDto.class);
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Category.class, CategoryDto.class)
                .addMappings(m -> m.skip(CategoryDto::setParentCategoryId)).setPostConverter(toDtoConverter())
        ;
        mapper.createTypeMap(CategoryDto.class, Category.class)
                .addMappings(m -> m.skip(Category::setCategory)).setPostConverter(toEntityConverter())
        ;
    }

    public Converter<Category, CategoryDto> toDtoConverter() {
        return context -> {
            Category source = context.getSource();
            CategoryDto destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    public Converter<CategoryDto, Category> toEntityConverter() {
        return context -> {
            CategoryDto source = context.getSource();
            Category destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    public void mapSpecificFields(CategoryDto source, Category destination) {
        try {
            destination.setCategory(
                    Objects.isNull(source) || Objects.isNull(source.getParentCategoryId())
                            ? null
                            : categoryRepository.findById(source.getParentCategoryId())
                            .orElseThrow(() -> new NotFoundException("Category not found"))
            );
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void mapSpecificFields(Category source, CategoryDto destination) {
        destination.setParentCategoryId(
                Objects.isNull(source) || Objects.isNull(source.getId())
                        ? null
                        : source.getCategory().getId()
        );
    }

}
