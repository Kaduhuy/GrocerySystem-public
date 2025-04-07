package com.example.grocery_system.controller;

import com.example.grocery_system.model.Users;
import com.example.grocery_system.service.GroceryService;
import com.example.grocery_system.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class HomeController {
    private final GroceryService groceryService;
    private final UserService userService;

    public HomeController(GroceryService groceryService, UserService userService){
        this.groceryService = groceryService;
        this.userService = userService;
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
    public String processSignup(@ModelAttribute("userForm") Users userForm, Model model) {
        try {
            userService.registerUser(userForm.getName(), userForm.getEmail(), userForm.getPassword());
            return "redirect:/login";
        } catch (RuntimeException e) {
            model.addAttribute("signupError", e.getMessage());
            return "signup";
        }
    }

    @GetMapping("/home")
    public String home(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        if (keyword != null && !keyword.isEmpty()) {
            model.addAttribute("groceries", groceryService.searchGroceries(keyword));
        } else {
            model.addAttribute("groceries", groceryService.getAllGroceryItems());
        }
        return "home";
    }
}
