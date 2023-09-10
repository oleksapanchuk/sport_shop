package dev.oleksa.sportshop.mapper;

import dev.oleksa.sportshop.exception.NotFoundException;
import dev.oleksa.sportshop.model.dto.OrderDto;
import dev.oleksa.sportshop.model.order.UserOrder;
import dev.oleksa.sportshop.model.product.ProductItem;
import dev.oleksa.sportshop.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderMapper {

    private final ModelMapper mapper;
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;
    private final AddressRepository addressRepository;
    private final ShippingMethodRepository shippingMethodRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final ProductItemRepository productItemRepository;

    public UserOrder toEntity(OrderDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, UserOrder.class);
    }

    public OrderDto toDto(UserOrder entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, OrderDto.class);
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(UserOrder.class, OrderDto.class)
                .addMappings(m -> m.skip(OrderDto::setUserId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(OrderDto::setPaymentMethodId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(OrderDto::setShippingAddressId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(OrderDto::setShippingMethodId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(OrderDto::setOrderStatusId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(OrderDto::setProductIds)).setPostConverter(toDtoConverter())
        ;
        mapper.createTypeMap(OrderDto.class, UserOrder.class)
                .addMappings(m -> m.skip(UserOrder::setUser)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(UserOrder::setPaymentMethod)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(UserOrder::setShippingAddress)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(UserOrder::setShippingMethod)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(UserOrder::setOrderStatus)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(UserOrder::setProducts)).setPostConverter(toEntityConverter())
        ;
    }

    public Converter<UserOrder, OrderDto> toDtoConverter() {
        return context -> {
            UserOrder source = context.getSource();
            OrderDto destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    public Converter<OrderDto, UserOrder> toEntityConverter() {
        return context -> {
            OrderDto source = context.getSource();
            UserOrder destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    public void mapSpecificFields(OrderDto source, UserOrder destination) {
        try {
            destination.setUser(
                    Objects.isNull(source) || Objects.isNull(source.getUserId())
                            ? null
                            : userRepository.findById(source.getUserId())
                            .orElseThrow(() -> new NotFoundException("User not found"))
            );
            destination.setPaymentMethod(
                    Objects.isNull(source) || Objects.isNull(source.getPaymentMethodId())
                            ? null
                            : paymentRepository.findById(source.getPaymentMethodId())
                            .orElseThrow(() -> new NotFoundException("Payment Method not found"))
            );
            destination.setShippingAddress(
                    Objects.isNull(source) || Objects.isNull(source.getShippingAddressId())
                            ? null
                            : addressRepository.findById(source.getShippingAddressId())
                            .orElseThrow(() -> new NotFoundException("Payment Method not found"))
            );
            destination.setShippingMethod(
                    Objects.isNull(source) || Objects.isNull(source.getShippingMethodId())
                            ? null
                            : shippingMethodRepository.findById(source.getShippingMethodId())
                            .orElseThrow(() -> new NotFoundException("Shipping Method not found"))
            );
            destination.setOrderStatus(
                    Objects.isNull(source) || Objects.isNull(source.getOrderStatusId())
                            ? null
                            : orderStatusRepository.findById(source.getOrderStatusId())
                            .orElseThrow(() -> new NotFoundException("Order Status not found"))
            );
            destination.setProducts(
                    Objects.isNull(source) || Objects.isNull(source.getProductIds())
                            ? null
                            : getProductItems(source.getProductIds())
            );
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void mapSpecificFields(UserOrder source, OrderDto destination) {
        destination.setUserId(
                Objects.isNull(source) || Objects.isNull(source.getId())
                        ? null
                        : source.getUser().getId()
        );
        destination.setPaymentMethodId(
                Objects.isNull(source) || Objects.isNull(source.getId())
                        ? null
                        : source.getPaymentMethod().getId()
        );
        destination.setShippingAddressId(
                Objects.isNull(source) || Objects.isNull(source.getId())
                        ? null
                        : source.getShippingAddress().getId()
        );
        destination.setShippingMethodId(
                Objects.isNull(source) || Objects.isNull(source.getId())
                        ? null
                        : source.getShippingMethod().getId()
        );
        destination.setOrderStatusId(
                Objects.isNull(source) || Objects.isNull(source.getId())
                        ? null
                        : source.getOrderStatus().getId()
        );
        destination.setProductIds(
                Objects.isNull(source) || Objects.isNull(source.getId())
                        ? null
                        : getProductIds(source.getProducts())
        );
    }

    private Set<ProductItem> getProductItems(Set<Long> productsId) throws NotFoundException {
        Set<ProductItem> productItems = new HashSet<>();
        for (Long id : productsId) {
            productItems.add(productItemRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Product not found")));
        }
        return productItems;
    }

    private Set<Long> getProductIds(Set<ProductItem> products) {
        Set<Long> ids = new HashSet<>();
        for (ProductItem item : products) {
            ids.add(item.getId());
        }
        return ids;
    }
}
