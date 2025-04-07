package com.example.grocery_system.service;

import com.example.grocery_system.model.Category;
import com.example.grocery_system.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public Category addCategory(Category category){
        String upperCaseName = category.getName().toUpperCase();
        category.setName(upperCaseName);
        return categoryRepository.save(category);
    }

    public Category getCategoryById(Long id){
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category was not found with ID: " + id));
    }

    public Category updateCategory(Long id, Category updatedCategory){
        if(categoryRepository.existsById(id)){
            updatedCategory.setId(id);
            String upperCaseName = updatedCategory.getName().toUpperCase();
            updatedCategory.setName(upperCaseName);
            return categoryRepository.save(updatedCategory);
        }
        return null;
    }

    public boolean deleteCategory(Long id){
        if(categoryRepository.existsById(id)){
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }
}
