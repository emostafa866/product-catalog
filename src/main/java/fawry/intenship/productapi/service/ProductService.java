package fawry.intenship.productapi.service;

import fawry.intenship.productapi.entities.Product;

import java.util.List;

public interface ProductService {

    public Product addProduct(Product product);

    public List<Product> getAllProducts();

    public Product getProductById(Long id);

    public List<Product>findByCategoryId(Long id);

    public void deleteProduct(Long id);

    public Product update(Product product);

    public List<Product>getAllByPopularity();
}
