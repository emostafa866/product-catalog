package fawry.intenship.productapi.service.impl;


import fawry.intenship.productapi.dto.OrderDto;
import fawry.intenship.productapi.dto.ProductDto;
import fawry.intenship.productapi.entities.Order;
import fawry.intenship.productapi.entities.OrderDetail;
import fawry.intenship.productapi.entities.Product;
import fawry.intenship.productapi.errors.QuantityException;
import fawry.intenship.productapi.errors.RecordNotFoundException;
import fawry.intenship.productapi.repository.OrderRepo;
import fawry.intenship.productapi.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class OrderService implements fawry.intenship.productapi.service.OrderService {
    @Autowired
    private OrderRepo orderRebo;
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepo productRepository;

    public Order createOrder(Order order){

        return orderRebo.save(order);
    }

    public List<Order>CreateListOfOrders(List<Order>orders){

        return orderRebo.saveAll(orders);
    }
    public List<Order> getAllOrders(){

        return orderRebo.findAll();
    }

    public Order getOrderById(Long id){

        return orderRebo.findById(id).orElseThrow(()->new RecordNotFoundException("id "+ id + " not exist"));
    }

    public void deleteOrder(Long id){

        orderRebo.deleteById(id);
    }

    public Long addNewOrder(OrderDto orderDto){
        Order order=new Order();
        order.setNumberOfItems((long) orderDto.getProducts().size());
        order.setOrderDetails(new ArrayList<>());
        for (ProductDto productDto : orderDto.getProducts()) {
            Product product = productService.getProductById(productDto.getProductId());
            if (productDto.getQuantity()<=product.getQuantity()){
            order.getOrderDetails().add(new OrderDetail(productDto.getQuantity(), product, order));
            product.setQuantity(product.getQuantity()-productDto.getQuantity());
            productRepository.saveAndFlush(product);
            }else {
                throw new QuantityException("quantity is not available");
            }
        }
        orderRebo.saveAndFlush(order);
        return order.getId();
      }

}

