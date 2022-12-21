package fawry.intenship.productapi.service.impl;


import fawry.intenship.productapi.dto.OrderDto;
import fawry.intenship.productapi.dto.ProductDto;
import fawry.intenship.productapi.entities.Order;
import fawry.intenship.productapi.entities.OrderDetail;
import fawry.intenship.productapi.entities.Product;
import fawry.intenship.productapi.errors.ConflictException;
import fawry.intenship.productapi.errors.RecordNotFoundException;
import fawry.intenship.productapi.repository.OrderRepo;
import fawry.intenship.productapi.repository.ProductRepo;
import fawry.intenship.productapi.security.appUser.AppUser;
import fawry.intenship.productapi.security.appUser.AppUserRepo;
import fawry.intenship.productapi.security.appUser.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class
OrderService implements fawry.intenship.productapi.service.OrderService {
    @Autowired
    private OrderRepo orderRebo;
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepo productRepository;
    @Autowired
    AppUserRepo appUserRepo;



    public List<Order> getAllOrders(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String cuurentUser= (String) auth.getPrincipal();
       AppUser newUser =  appUserRepo.findByEmail(cuurentUser).orElseThrow(null);
        return newUser.getOrders();

    }

    public Order getOrderById(Long id){

        return orderRebo.findById(id)
                .orElseThrow(()->new RecordNotFoundException("id "+ id + " not exist"));
    }

    public void deleteOrder(Long id){

        orderRebo.deleteById(id);
    }

    public Long createOrder(OrderDto orderDto){
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
                throw new ConflictException("quantity is not available");
            }
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = auth.getName();
        AppUser currentUser = appUserRepo.findByEmail(currentUserName).orElseThrow(null);
        order.setUser(currentUser);
        orderRebo.saveAndFlush(order);
        return order.getId();
      }

}

