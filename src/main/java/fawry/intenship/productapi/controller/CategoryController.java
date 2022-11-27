package fawry.intenship.productapi.controller;

import fawry.intenship.productapi.entities.Category;
import fawry.intenship.productapi.entities.Product;
import fawry.intenship.productapi.service.impl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("")
    public Category createCategory(@RequestBody Category category){

        return categoryService.createCategory(category);
    }

    @GetMapping("")
    public List<Category> getAllCategories(){

        return categoryService.getAllCategories();
    }

    @GetMapping("{id}")
    public Category getOneCategory(@PathVariable Long id){

        return categoryService.getOneCategory(id);
    }

    /*public List<Product>findByCategory(String categoryName){
        return
    }*/

}
