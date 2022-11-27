package fawry.intenship.productapi.service;

import fawry.intenship.productapi.entities.Category;

import java.util.List;

public interface CategoryService {

    public Category createCategory(Category category);

    public List<Category> getAllCategories();

    public Category getOneCategory(Long id);
}
