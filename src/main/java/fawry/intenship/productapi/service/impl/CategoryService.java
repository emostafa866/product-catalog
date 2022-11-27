package fawry.intenship.productapi.service.impl;

import fawry.intenship.productapi.entities.Category;
import fawry.intenship.productapi.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryService implements fawry.intenship.productapi.service.CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public Category createCategory(Category category){

        return categoryRepo.save(category);
    }

    public List<Category> getAllCategories(){

        return categoryRepo.findAll();
    }

    public Category getOneCategory(Long id){

        return categoryRepo.findById(id).orElse(null);
    }
}
