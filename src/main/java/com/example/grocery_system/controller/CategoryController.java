package com.example.grocery_system.controller;

import com.example.grocery_system.model.Category;
import com.example.grocery_system.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/categories/add")
    public String showAddCategoryForm(@RequestParam(value = "sort", required = false) String sort,
                                      Model model){
        List<Category> categories;
        if(sort != null && !sort.isEmpty()){
            if("asc".equalsIgnoreCase(sort)){
                categories = categoryService.getCategoriesSortedByAsc();
            }else if("desc".equalsIgnoreCase(sort)){
                categories = categoryService.getCategoriesSortedByDesc();
            }
            else{
                categories = categoryService.getAllCategories();
            }
        }else{
            categories = categoryService.getAllCategories();
        }
        model.addAttribute("category", new Category());
        model.addAttribute("categories", categories);
        model.addAttribute("selectedSort", sort);
        return "add-category";
    }

    @PostMapping("/categories/add")
    public String addCategory(@ModelAttribute Category category,
                              Model model){
        try{
            categoryService.addCategory(category);
            return "redirect:/categories/add";
        }catch (RuntimeException e){
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("category", new Category());
            model.addAttribute("categories", categoryService.getAllCategories());
            return "add-category";
        }
    }

    @GetMapping("/categories/{id}/edit")
    public String showEditCategoryForm(@PathVariable Long id, Model model){
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "edit-category";
    }

    @PostMapping("/categories/{id}/edit")
    public String updateCategory(@PathVariable Long id,
                                 @ModelAttribute Category category,
                                 Model model){
        try {
            categoryService.updateCategory(id, category);
            return "redirect:/categories/add";
        }catch (RuntimeException e){
            Category cat = categoryService.getCategoryById(id);
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("category", cat);
            return "edit-category";
        }
    }

    @DeleteMapping("/categories/{id}")
    public String deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return "redirect:/categories/add";
    }
}
