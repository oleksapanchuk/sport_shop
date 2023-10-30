package dev.oleksa.sportshop.service.impl;

import dev.oleksa.sportshop.dto.OrderDto;
import dev.oleksa.sportshop.mapper.OrderMapper;
import dev.oleksa.sportshop.model.order.OrderStatus;
import dev.oleksa.sportshop.model.order.ShippingMethod;
import dev.oleksa.sportshop.repository.OrderRepository;
import dev.oleksa.sportshop.repository.OrderStatusRepository;
import dev.oleksa.sportshop.repository.ShippingMethodRepository;
import dev.oleksa.sportshop.service.OrderService;
import dev.oleksa.sportshop.service.ShippingMethodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final ShippingMethodRepository shippingMethodRepository;
    private final OrderMapper orderMapper;


    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        var order = orderMapper.toEntity(orderDto);

        order = orderRepository.save(order);

        return orderMapper.toDto(order);
    }

    @Override
    public OrderDto readOrder(Long orderId) {
        return orderMapper.toDto(
                orderRepository.findById(orderId)
                        .orElseThrow()
        );
    }

    @Override
    public OrderDto updateOrder(OrderDto orderDto) {
        var order = orderMapper.toEntity(orderDto);

        order = orderRepository.save(order);

        return orderMapper.toDto(order);
    }

    @Override
    public Boolean deleteOrder(Long orderId) {
        try {
            orderRepository.deleteById(orderId);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public List<OrderStatus> getOrderStatuses() {
        return orderStatusRepository.findAll();
    }

    @Override
    public OrderStatus getOrderStatus(Long orderStatusId) {
        return orderStatusRepository.findById(orderStatusId)
                .orElseThrow();
    }

    @Override
    public List<ShippingMethod> getShippingMethods() {
        return shippingMethodRepository.findAll();
    }

    @Override
    public ShippingMethod getShippingMethod(Long shippingMethodId) {
        return shippingMethodRepository.findById(shippingMethodId)
                .orElseThrow();
    }
}
