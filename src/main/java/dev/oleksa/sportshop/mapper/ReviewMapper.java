package dev.oleksa.sportshop.mapper;

import dev.oleksa.sportshop.dto.ReviewDto;
import dev.oleksa.sportshop.exception.NotFoundException;
import dev.oleksa.sportshop.model.review.Review;
import dev.oleksa.sportshop.repository.ProductRepository;
import dev.oleksa.sportshop.repository.UserRepository;
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
public class ReviewMapper {

    private final ModelMapper mapper;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public Review toEntity(ReviewDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Review.class);
    }

    public ReviewDto toDto(Review entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, ReviewDto.class);
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Review.class, ReviewDto.class)
                .addMappings(m -> m.skip(ReviewDto::setUserId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(ReviewDto::setProductId)).setPostConverter(toDtoConverter())
        ;
        mapper.createTypeMap(ReviewDto.class, Review.class)
                .addMappings(m -> m.skip(Review::setUser)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(Review::setProduct)).setPostConverter(toEntityConverter())
        ;
    }

    public Converter<Review, ReviewDto> toDtoConverter() {
        return context -> {
            Review source = context.getSource();
            ReviewDto destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    public Converter<ReviewDto, Review> toEntityConverter() {
        return context -> {
            ReviewDto source = context.getSource();
            Review destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    public void mapSpecificFields(ReviewDto source, Review destination) {
        try {
            destination.setUser(
                    Objects.isNull(source) || Objects.isNull(source.getUserId())
                            ? null
                            : userRepository.findById(source.getUserId())
                            .orElseThrow(() -> new NotFoundException("User not found"))
            );
            destination.setProduct(
                    Objects.isNull(source) || Objects.isNull(source.getUserId())
                            ? null
                            : productRepository.findById(source.getProductId())
                            .orElseThrow(() -> new NotFoundException("Product Item not found"))
            );
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void mapSpecificFields(Review source, ReviewDto destination) {
        destination.setUserId(
                Objects.isNull(source) || Objects.isNull(source.getId())
                        ? null
                        : source.getUser().getId()
        );
        destination.setProductId(
                Objects.isNull(source) || Objects.isNull(source.getId())
                        ? null
                        : source.getProduct().getId()
        );
    }
}
