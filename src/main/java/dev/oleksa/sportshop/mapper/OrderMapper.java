package dev.oleksa.sportshop.mapper;

import dev.oleksa.sportshop.exception.NotFoundException;
import dev.oleksa.sportshop.dto.OrderDto;
import dev.oleksa.sportshop.model.order.Order;
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

    public Order toEntity(OrderDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Order.class);
    }

    public OrderDto toDto(Order entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, OrderDto.class);
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Order.class, OrderDto.class)
                .addMappings(m -> m.skip(OrderDto::setUserId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(OrderDto::setPaymentMethodId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(OrderDto::setShippingAddressId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(OrderDto::setShippingMethodId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(OrderDto::setOrderStatusId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(OrderDto::setProductIds)).setPostConverter(toDtoConverter())
        ;
        mapper.createTypeMap(OrderDto.class, Order.class)
                .addMappings(m -> m.skip(Order::setUser)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(Order::setPaymentMethod)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(Order::setShippingAddress)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(Order::setShippingMethod)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(Order::setOrderStatus)).setPostConverter(toEntityConverter())
        ;
    }

    public Converter<Order, OrderDto> toDtoConverter() {
        return context -> {
            Order source = context.getSource();
            OrderDto destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    public Converter<OrderDto, Order> toEntityConverter() {
        return context -> {
            OrderDto source = context.getSource();
            Order destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    public void mapSpecificFields(OrderDto source, Order destination) {
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
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void mapSpecificFields(Order source, OrderDto destination) {
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
