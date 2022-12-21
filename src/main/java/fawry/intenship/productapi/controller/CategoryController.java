package fawry.intenship.productapi.controller;

import fawry.intenship.productapi.entities.Category;
import fawry.intenship.productapi.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @PostMapping
    public Category createCategory(@RequestBody Category category){

        return categoryService.createCategory(category);
    }

    @GetMapping
    public List<Category> getAllCategories(){

        return categoryService.getAllCategories();
    }

    @GetMapping("{id}")
    public Category getOneCategory(@PathVariable Long id){

        return categoryService.getOneCategory(id);
    }
    @GetMapping("/get/{categoryName}")
    public Category getCategoryByName(@PathVariable String categoryName ){
        return categoryService.findByName(categoryName);
    }


}
