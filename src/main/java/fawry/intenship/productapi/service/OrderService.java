package fawry.intenship.productapi.service;

import fawry.intenship.productapi.dto.OrderDto;
import fawry.intenship.productapi.entities.Order;

import java.util.List;

public interface OrderService {

    public List<Order> getAllOrders();

    public Order getOrderById(Long id);

    public void deleteOrder(Long id);

    public Long createOrder(OrderDto orderDto);
}
