package dev.oleksa.sportshop.service;

import dev.oleksa.sportshop.model.dto.OrderDto;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);

    OrderDto readOrder(Long orderId);

    OrderDto updateOrder(OrderDto orderDto);

    Boolean deleteOrder(Long orderId);
}
