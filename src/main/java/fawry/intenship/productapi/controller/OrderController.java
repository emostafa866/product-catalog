package fawry.intenship.productapi.controller;

import fawry.intenship.productapi.dto.OrderDto;
import fawry.intenship.productapi.entities.Order;
import fawry.intenship.productapi.service.impl.OrderService;
import fawry.intenship.productapi.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;


    @PostMapping
    public Long createOrder(@RequestBody OrderDto orderDto){

        return orderService.createOrder(orderDto);
    }


    @GetMapping()
    public List<Order> getAllOrders(){

        return orderService.getAllOrders();
    }


    @GetMapping("{id}")
    public Order getOrder(@PathVariable Long id){

        return orderService.getOrderById(id);
    }


    @DeleteMapping("{id}")
    public void cancelOrder(@PathVariable Long id){

        orderService.deleteOrder(id);
    }


}