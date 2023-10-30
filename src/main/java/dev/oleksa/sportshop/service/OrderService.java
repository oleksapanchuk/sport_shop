package dev.oleksa.sportshop.service;

import dev.oleksa.sportshop.dto.OrderDto;
import dev.oleksa.sportshop.model.order.OrderStatus;
import dev.oleksa.sportshop.model.order.ShippingMethod;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);
    OrderDto readOrder(Long orderId);
    OrderDto updateOrder(OrderDto orderDto);
    Boolean deleteOrder(Long orderId);


    List<OrderStatus> getOrderStatuses();
    OrderStatus getOrderStatus(Long orderStatusId);
    List<ShippingMethod> getShippingMethods();
    ShippingMethod getShippingMethod(Long shippingMethodId);

}
