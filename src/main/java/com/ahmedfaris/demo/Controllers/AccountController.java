package com.ahmedfaris.demo.Controllers;


import com.ahmedfaris.demo.Models.AppUser;
import com.ahmedfaris.demo.Models.Role;
import com.ahmedfaris.demo.Repositories.RoleRepository;
import com.ahmedfaris.demo.Repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import com.ahmedfaris.demo.Models.RegisterDto;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class AccountController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/register")
    public String register(Model model) {
        RegisterDto registerDto = new RegisterDto();
        model.addAttribute(registerDto);
        model.addAttribute("success", false);
        return "register";
    }

    @PostMapping("/register")
    public String register(Model model,
                           @Valid @ModelAttribute RegisterDto registerDto,
                           BindingResult bindingResult) {
        if(!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            bindingResult.addError(
                    new FieldError("registerDto", "confirmPassword", "Passwords do not match")
            );
        }

        Optional<AppUser> appUser = userRepository.findByEmail(registerDto.getEmail());
        if(appUser.isPresent()) {
            bindingResult.addError(
                    new FieldError("registerDto", "email", "Email address already in use")
            );
        }

        if (bindingResult.hasErrors()){
            return "register";
        }

        try {
            var BCryptEncoder = new BCryptPasswordEncoder();

            AppUser user = new AppUser();
            user.setUsername(registerDto.getName());
            user.setEmail(registerDto.getEmail());
            user.setPassword(BCryptEncoder.encode(registerDto.getPassword()));

            Role userRole = roleRepository.findByName("USER");
            user.setRole(userRole);

            userRepository.save(user);

            model.addAttribute("registerDto", new RegisterDto());
            model.addAttribute("success", true);
        }
        catch (Exception e) {
            bindingResult.addError(
                    new FieldError("registerDto", "email", e.getMessage())
            );
        }

        return "register";
     }
}
