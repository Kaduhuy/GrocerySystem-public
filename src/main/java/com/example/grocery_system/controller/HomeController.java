package com.example.grocery_system.controller;

import com.example.grocery_system.model.Category;
import com.example.grocery_system.model.GroceryItem;
import com.example.grocery_system.model.Users;
import com.example.grocery_system.service.CategoryService;
import com.example.grocery_system.service.GroceryService;
import com.example.grocery_system.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class HomeController {
    private final GroceryService groceryService;
    private final UserService userService;
    private final CategoryService categoryService;

    public HomeController(GroceryService groceryService,
                          UserService userService,
                          CategoryService categoryService){
        this.groceryService = groceryService;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String redirectToHomePage() {
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("userForm", new Users());
        return "signup";
    }

    @PostMapping("/signup")
    public String processSignup(@ModelAttribute("userForm") Users userForm,
                                @RequestParam("confirmPassword") String confirmPassword,
                                Model model) {

        if (!userForm.getPassword().equals(confirmPassword)) {
            model.addAttribute("signupError", "Passwords do not match.");
            return "signup";
        }
        try {
            userService.registerUser(userForm.getName(), userForm.getEmail(), userForm.getPassword());
            return "redirect:/login";
        } catch (RuntimeException e) {
            model.addAttribute("signupError", e.getMessage());
            return "signup";
        }
    }

    @GetMapping("/home")
    public String home(@RequestParam(value = "keyword", required = false) String keyword,
                       @RequestParam(value = "category", required = false) String categoryName,
                       @RequestParam(value = "sort", required = false) String sort,
                       Model model) {

        List<GroceryItem> groceries;

        if (keyword != null && !keyword.isEmpty()) {
            groceries = groceryService.searchGroceries(keyword);
        } else if (categoryName != null && !categoryName.isEmpty()) {
            Category category = categoryService.getCategoryByName(categoryName);
            groceries = groceryService.filterGroceriesByCategory(category);
            model.addAttribute("selectedCategory", category);
        } else if (sort != null && !sort.isEmpty()) {
            if ("asc".equalsIgnoreCase(sort)) {
                groceries = groceryService.getGroceriesSortedByNameAsc();
            } else if ("desc".equalsIgnoreCase(sort)) {
                groceries = groceryService.getGroceriesSortedByNameDesc();
            } else {
                groceries = groceryService.getAllGroceryItems(); // fallback
            }
        } else {
            groceries = groceryService.getAllGroceryItems();
        }

        model.addAttribute("groceries", groceries);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("keyword", keyword);
        model.addAttribute("selectedSort", sort);

        return "home";
    }
}
