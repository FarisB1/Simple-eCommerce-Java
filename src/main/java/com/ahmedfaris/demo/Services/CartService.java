package com.ahmedfaris.demo.Services;

import com.ahmedfaris.demo.Models.AppUser;
import com.ahmedfaris.demo.Models.Cart;
import com.ahmedfaris.demo.Models.Product;
import com.ahmedfaris.demo.Repositories.CartDao;
import com.ahmedfaris.demo.Repositories.ProductRepository;
import com.ahmedfaris.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CartService {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public Cart addToCart(Integer productId, Integer quantity) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + productId));


        String currentUser = getCurrentUsername();
        if (currentUser == null) {
            throw new IllegalStateException("No authenticated user found.");
        }


        AppUser user = userRepository.findByEmail(currentUser)
                .orElseThrow(() -> new IllegalArgumentException("User not found with username: " + currentUser));


        Optional<Cart> existingCart = cartDao.findByUserAndProduct(user, product);
        if (existingCart.isPresent()) {
            Cart cart = existingCart.get();
            cart.setQuantity(cart.getQuantity() + quantity);
            return cartDao.save(cart);
        }

        Cart cart = new Cart(product, user, quantity);
        return cartDao.save(cart);
    }

    public String getCurrentUsername() {
        Authentication authenticationToken = SecurityContextHolder.getContext().getAuthentication();
        if (authenticationToken != null && authenticationToken.isAuthenticated()) {
            Object principal = authenticationToken.getPrincipal();
            if (principal instanceof String) {
                return (String) principal;
            } else if (principal instanceof org.springframework.security.core.userdetails.User) {
                return ((org.springframework.security.core.userdetails.User) principal).getUsername();
            }
        }
        return null;
    }

    public List<Cart> getCartItems() {
        String currentUser = getCurrentUsername();
        AppUser user = userRepository.findByEmail(currentUser)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return StreamSupport.stream(cartDao.findAll().spliterator(), false)
                .filter(cart -> cart.getUser().getId().equals(user.getId()))
                .collect(Collectors.toList());
    }

    public double calculateTotalPrice() {
        return getCartItems().stream()
                .mapToDouble(cart -> cart.getProduct().getPrice() * cart.getQuantity()) // Multiply by quantity
                .sum();
    }

    public void removeFromCart(Integer cartItemId) {
        cartDao.deleteById(cartItemId);
    }
}
