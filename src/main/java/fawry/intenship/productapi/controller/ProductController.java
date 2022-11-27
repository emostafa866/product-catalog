package fawry.intenship.productapi.controller;



import fawry.intenship.productapi.entities.Product;
import fawry.intenship.productapi.service.impl.CategoryService;
import fawry.intenship.productapi.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;


    @GetMapping("/product")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts(){

        return productService.getAllProducts();
    }


    @GetMapping("/product/popular")
    @ResponseStatus(HttpStatus.OK)
    public List<Product>getAllByPopularity(){

        return productService.getAllByPopularity();
    }


    @GetMapping("/product/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@PathVariable Long id){

        return productService.getProductById(id);
    }


    @PostMapping("/category/{id}/product")
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@PathVariable Long id,@RequestBody Product product){
        product.setCategory(categoryService.getOneCategory(id));
        return productService.addProduct(product);
    }


    @DeleteMapping("/product/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable Long id){

        productService.deleteProduct(id);
    }


    @PutMapping("/product")
    @ResponseStatus(HttpStatus.CREATED)
    public Product update(@RequestBody Product product){

        return productService.addProduct(product);
    }


    @GetMapping("category/{id}/product")
    @ResponseStatus(HttpStatus.OK)
    public List<Product>findByCategoryId(@PathVariable Long id){

        return productService.findByCategoryId(id);
    }



}
