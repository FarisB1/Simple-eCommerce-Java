package com.ahmedfaris.demo.Controllers;

import com.ahmedfaris.demo.Models.AppUser;
import com.ahmedfaris.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    // Get all users
    @GetMapping("/")
    public List<AppUser> getAllUsers() {
        return userService.findAll();
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable Integer id) {
        Optional<AppUser> user = userService.findById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // Create new user
    @PostMapping("/")
    public ResponseEntity<AppUser> createUser(@Valid @RequestBody AppUser appUser, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // Encode the password before saving
        appUser.setPassword(appUser.getPassword());

        AppUser savedAppUser = userService.save(appUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAppUser);
    }

    // Update existing user
    @PutMapping("/{id}")
    public ResponseEntity<AppUser> updateUser(@PathVariable Integer id, @Valid @RequestBody AppUser appUserDetails) {
        Optional<AppUser> user = userService.findById(id);
        if (user.isPresent()) {
            AppUser existingAppUser = user.get();

            // Update fields
            existingAppUser.setUsername(appUserDetails.getUsername());
            existingAppUser.setEmail(appUserDetails.getEmail());
            existingAppUser.setRole(appUserDetails.getRole());

            // Encode password if updated
            if (appUserDetails.getPassword() != null && !appUserDetails.getPassword().isEmpty()) {
                existingAppUser.setPassword(appUserDetails.getPassword());
            }

            AppUser updatedAppUser = userService.save(existingAppUser);
            return ResponseEntity.ok(updatedAppUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        Optional<AppUser> user = userService.findById(id);
        if (user.isPresent()) {
            userService.delete(user.get().getId());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.delete(id);
        return "redirect:/users"; // Redirect to the users list after deletion
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable int id, Model model) {
        AppUser user = userService.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("user", user);
        return "edit_user"; // You should create an 'editUser.html' view for this
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable int id, @ModelAttribute AppUser user, Model model) {
        AppUser existingUser = userService.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        // Update user fields
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setRole(user.getRole());  // Ensure role is properly updated

        // Save the updated user back to the database
        userService.save(existingUser);

        return "redirect:/users";  // Redirect back to the list of users
    }

}
