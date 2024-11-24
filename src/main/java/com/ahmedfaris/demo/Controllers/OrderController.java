package com.ahmedfaris.demo.Controllers;

import com.ahmedfaris.demo.Models.*;
import com.ahmedfaris.demo.Repositories.OrderRepository;
import com.ahmedfaris.demo.Repositories.UserRepository;
import com.ahmedfaris.demo.Services.CartService;
import com.ahmedfaris.demo.Services.OrderItemService;
import com.ahmedfaris.demo.Services.OrderService;
import com.ahmedfaris.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private final UserService userService;
    @Autowired
    private final CartService cartService;
    @Autowired
    private final OrderService orderService;
    @Autowired
    private final OrderItemService orderItemService;

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    public OrderController(UserService userService, CartService cartService, OrderService orderService, OrderItemService orderItemService, UserRepository userRepository) {
        this.userService = userService;
        this.cartService = cartService;
        this.orderService = orderService;
        this.orderItemService = orderItemService;
        this.userRepository = userRepository;
    }

    @PostMapping("/checkout")
    public String checkout(Model model) {
        String currentUser = userService.getCurrentUsername();
        if (currentUser == null) {
            model.addAttribute("error", "No authenticated user found.");
            return "checkout_error";
        }

        AppUser user = userRepository.findByEmail(currentUser)
                .orElseThrow(() -> new IllegalArgumentException("User not found with username: " + currentUser));

        List<Cart> cartItems = cartService.getCartItems();

        if (cartItems.isEmpty()) {
            model.addAttribute("error", "Cart is empty");
            return "checkout_error";
        }

        try {
            Order order = orderService.createOrder(user);

            for (Cart item : cartItems) {
                Product product = item.getProduct();
                int quantity = item.getQuantity();
                OrderItem orderItem = orderItemService.createOrderItem(order, product, quantity);
            }

            model.addAttribute("order", order);
            return "checkout_success";
        } catch (Exception e) {
            model.addAttribute("error", "Checkout failed: " + e.getMessage());
            return "checkout_error";
        }
    }

    @GetMapping("/orders")
    public String orders(Model model) {
        String currentUser = userService.getCurrentUsername();
        if (currentUser == null) {
            model.addAttribute("error", "No authenticated user found.");
            return "checkout_error";
        }

        AppUser user = userRepository.findByEmail(currentUser)
                .orElseThrow(() -> new IllegalArgumentException("User not found with username: " + currentUser));

        model.addAttribute("orders",orderService.getOrdersByUser(user));
        return "orders";
    }
}
