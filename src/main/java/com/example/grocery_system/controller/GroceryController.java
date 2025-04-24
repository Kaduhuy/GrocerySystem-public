package com.example.grocery_system.controller;

import com.example.grocery_system.model.GroceryItem;
import com.example.grocery_system.repository.CategoryRepository;
import com.example.grocery_system.service.GroceryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GroceryController {
    private final GroceryService groceryService;
    private final CategoryRepository categoryRepository;

<<<<<<< HEAD
    public GroceryController(GroceryService groceryService,
                             CategoryRepository categoryRepository){
=======
    public GroceryController(GroceryService groceryService, CategoryRepository categoryRepository){
>>>>>>> aea768050c8b39c347dfeba62a25b6b8079d851e
        this.groceryService = groceryService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/groceries/add")
    public String showAddGroceryForm(Model model) {
        model.addAttribute("groceryItem", new GroceryItem());
        model.addAttribute("categories", categoryRepository.findAll());
        return "add-grocery";
    }

    @PostMapping("/groceries/add")
<<<<<<< HEAD
    public String addGrocery(@ModelAttribute GroceryItem groceryItem,
                             Model model) {
        try{
            groceryService.addGroceryItem(groceryItem);
            return "redirect:/home";
        }catch (RuntimeException e){
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("groceryItem", groceryItem);
            return "add-grocery";
        }
=======
    public String addGrocery(@ModelAttribute GroceryItem groceryItem) {
        groceryService.addGroceryItem(groceryItem);
        return "redirect:/home";
>>>>>>> aea768050c8b39c347dfeba62a25b6b8079d851e
    }

    @GetMapping("/groceries/{id}/edit")
    public String showEditGroceryForm(@PathVariable Long id, Model model) {
        GroceryItem item = groceryService.getGroceryItemById(id.toString());
        model.addAttribute("groceryItem", item);
        model.addAttribute("categories", categoryRepository.findAll());
        return "edit-grocery";
    }

    @PostMapping("/groceries/{id}/edit")
<<<<<<< HEAD
    public String updateGrocery(@PathVariable Long id,
                                @ModelAttribute GroceryItem groceryItem,
                                Model model) {
        try{
            groceryService.updateGroceryItem(id, groceryItem);
            return "redirect:/home";
        }catch (RuntimeException e){
            GroceryItem item = groceryService.getGroceryItemById(id.toString());
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("groceryItem", item);
            model.addAttribute("categories", categoryRepository.findAll());
            return "edit-grocery";
        }
=======
    public String updateGrocery(@PathVariable Long id, @ModelAttribute GroceryItem groceryItem) {
        groceryService.updateGroceryItem(id, groceryItem);
        return "redirect:/home";
>>>>>>> aea768050c8b39c347dfeba62a25b6b8079d851e
    }

    @DeleteMapping("/groceries/{id}")
    public String deleteGrocery(@PathVariable Long id) {
        groceryService.deleteGroceryItem(id);
        return "redirect:/home";  // reloads the home page after deletion
    }
}
