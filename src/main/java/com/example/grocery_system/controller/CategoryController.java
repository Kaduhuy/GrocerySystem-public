package com.example.grocery_system.controller;

import com.example.grocery_system.model.Category;
import com.example.grocery_system.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/categories/add")
    public String showAddCategoryForm(Model model){
        model.addAttribute("category", new Category());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "add-category";
    }

    @PostMapping("/categories/add")
    public String addCategory(@ModelAttribute Category category){
        categoryService.addCategory(category);
        return "redirect:/categories/add";
    }

    @GetMapping("/categories/{id}/edit")
    public String showEditCategoryForm(@PathVariable Long id, Model model){
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "edit-category";
    }

    @PostMapping("/categories/{id}/edit")
    public String updateCategory(@PathVariable Long id, @ModelAttribute Category category){
        categoryService.updateCategory(id, category);
        return "redirect:/categories/add";
    }

    @DeleteMapping("/categories/{id}")
    public String deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return "redirect:/categories/add";
    }
}
