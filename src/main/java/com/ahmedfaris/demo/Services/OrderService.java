package com.ahmedfaris.demo.Services;

import com.ahmedfaris.demo.Models.*;
import com.ahmedfaris.demo.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {


    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final CartDao cartRepository;
    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository, CartDao cartRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }


    @Transactional
    public Order createOrder(AppUser user) {
        List<Cart> cartItems = cartRepository.findByUser(user); // Get cart items for user
        if (cartItems.isEmpty() || user == null) {
            throw new IllegalStateException("Cart is empty or User is null!");
        }

        // Create new order
        Order order = new Order();
        order.setUser(user);

        List<OrderItem> orderItems = new ArrayList<>();
        double totalPrice = 0.0;

        // Set initial order details
        order.setStatus("Pending");
        order.setOrderDate(LocalDateTime.now()); // Set the order date to current time

        // Save the order first, without order items
        orderRepository.save(order);  // Save the order to the database
        orderRepository.flush();  // Ensure the order is persisted and order_id is set

        // Now, process the cart items and create OrderItems
        for (Cart cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());

            // Set the price of the OrderItem from the product's price
            orderItem.setPrice(cartItem.getProduct().getPrice());

            // Calculate the total price by multiplying quantity and price of the product
            totalPrice += cartItem.getProduct().getPrice() * cartItem.getQuantity();

            // Set the order for the OrderItem
            orderItem.setOrder(order);  // Set the order reference for the order item
            orderItems.add(orderItem);

            // Save the OrderItem to the database
            orderItemRepository.save(orderItem);// Save the individual OrderItem to the database
        }

        // Set the total price for the order after adding all order items
        order.setTotalPrice(totalPrice);  // Set the total price for the order

        // Finally, update the order with the order items
        order.setOrderItems(orderItems);  // Set the order items list to the order
        orderRepository.save(order);  // Update the order with the order items
        cartRepository.deleteAll();

        return order;
    }




    public Object getOrdersByUser(AppUser user) {
        if(user == null) {
            throw new IllegalArgumentException("user cannot be null");
        }
        return orderRepository.findByUser(user);
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }
}
