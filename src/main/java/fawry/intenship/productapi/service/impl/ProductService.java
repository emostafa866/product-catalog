package fawry.intenship.productapi.service.impl;

import fawry.intenship.productapi.entities.Product;
import fawry.intenship.productapi.errors.ConflictException;
import fawry.intenship.productapi.errors.RecordNotFoundException;
import fawry.intenship.productapi.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements fawry.intenship.productapi.service.ProductService {
    @Autowired
    private ProductRepo productRepo;


    public Product addProduct(Product product){
        if(productRepo.findByNameEn(product.getNameEn())!=null){
            throw new ConflictException("Another product with the same title "+product.getNameEn()+" exists");
        }
        return productRepo.save(product);
    }

    public List<Product> getAllProducts(){

        return productRepo.findAll();
    }

    public Product getProductById(Long id){

        return productRepo.findById(id)
                .orElseThrow(()->new RecordNotFoundException("id " + id +" doesn't exsit"));
    }


    public List<Product>findByCategoryId(Long id){

        return productRepo.findByCategoryId(id);
    }

    public void deleteProduct(Long id){

        productRepo.deleteById(id);
    }

    public Product update(Product product){

        return productRepo.save(product);
    }

    public List<Product>getAllByPopularity(){
        List<Product>products=productRepo.findAll();
        List<Product>sorted=products.stream()
                .sorted((o1, o2) ->o2.getOrderDetails().size()- o1.getOrderDetails().size())
                .collect(Collectors.toList());

        return sorted;
    }

    @Override
    public List<Product> findByCategoryName(String categoryName) {
        return productRepo.findByCategoryName(categoryName);
    }
}
