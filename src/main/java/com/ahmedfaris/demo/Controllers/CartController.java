package com.ahmedfaris.demo.Controllers;


import com.ahmedfaris.demo.Models.Cart;
import com.ahmedfaris.demo.Services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public String viewCart(Model model) {
        model.addAttribute("cartItems", cartService.getCartItems());
        model.addAttribute("totalPrice", cartService.calculateTotalPrice());
        return "cart";
    }

    @PostMapping("/add/{productId}")
    public String addToCart(@PathVariable Integer productId, @RequestParam Integer quantity) {
        cartService.addToCart(productId, quantity);
        return "redirect:/cart";
    }


    @GetMapping("/remove/{cartItemId}")
    public String removeFromCart(@PathVariable Integer cartItemId) {
        cartService.removeFromCart(cartItemId);
        return "redirect:/cart";
    }


}
