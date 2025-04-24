package com.example.grocery_system.service;

import com.example.grocery_system.model.Category;
import com.example.grocery_system.repository.CategoryRepository;
<<<<<<< HEAD
import org.springframework.data.domain.Sort;
=======
>>>>>>> aea768050c8b39c347dfeba62a25b6b8079d851e
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public Category addCategory(Category category){
<<<<<<< HEAD
        if(checksIfCategoryNameExists(category)){
            throw new RuntimeException("This category already exists: " + category.getName());
        }
=======
>>>>>>> aea768050c8b39c347dfeba62a25b6b8079d851e
        String upperCaseName = category.getName().toUpperCase();
        category.setName(upperCaseName);
        return categoryRepository.save(category);
    }

<<<<<<< HEAD
    public boolean checksIfCategoryNameExists(Category category){
        for(Category cat : categoryRepository.findAll()){
            if(category.getName().equalsIgnoreCase(cat.getName())){
                return true;
            }
        }
        return false;
    }

    public Category getCategoryByName(String name){
        return categoryRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Category was not found with name: " + name));
    }

=======
>>>>>>> aea768050c8b39c347dfeba62a25b6b8079d851e
    public Category getCategoryById(Long id){
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category was not found with ID: " + id));
    }

    public Category updateCategory(Long id, Category updatedCategory){
        if(categoryRepository.existsById(id)){
<<<<<<< HEAD
            if(checksIfCategoryNameExists(updatedCategory)){
                throw new RuntimeException("This category already exists: " + updatedCategory.getName());
            }
=======
>>>>>>> aea768050c8b39c347dfeba62a25b6b8079d851e
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
<<<<<<< HEAD

    public List<Category> getCategoriesSortedByAsc(){
        return categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public List<Category> getCategoriesSortedByDesc(){
        return categoryRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
    }
=======
>>>>>>> aea768050c8b39c347dfeba62a25b6b8079d851e
}
