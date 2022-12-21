package fawry.intenship.productapi.dto;

import fawry.intenship.productapi.entities.Order;
import fawry.intenship.productapi.entities.Product;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

public class OrderDto {

    List<ProductDto>products;




    public List<ProductDto> getProducts() {

        return products;
    }

    public void setProducts(List<ProductDto> products) {

        this.products = products;
    }



}
