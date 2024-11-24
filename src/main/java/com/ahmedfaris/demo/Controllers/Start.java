package com.ahmedfaris.demo.Controllers;
import com.ahmedfaris.demo.Models.AppUser;
import com.ahmedfaris.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class Start {

    @ModelAttribute
    public void addUsernameToModel(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication != null ? authentication.getName() : "Guest";  // "Guest" if not authenticated
        model.addAttribute("username", username);  // Adds username to every page
    }
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/") // Handle the root URL
    public String home() {
        return "home"; // Return the home view
    }

    @GetMapping("/home") // Handle the root URL
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/shop")
    public String shop() {
        return "shop";
    }

    @GetMapping("/users") // Admin route to display all users
    public String listUsers(Model model) {
        List<AppUser> users = userRepository.findAll();
        model.addAttribute("users", users); // Pass users to the view
        return "users"; // This should match the name of your HTML file (e.g., `users.html`)
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
