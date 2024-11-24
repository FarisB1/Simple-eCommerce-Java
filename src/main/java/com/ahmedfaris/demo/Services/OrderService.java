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

        Order order = new Order();
        order.setUser(user);

        List<OrderItem> orderItems = new ArrayList<>();
        double totalPrice = 0.0;

        order.setStatus("Pending");
        order.setOrderDate(LocalDateTime.now());

        orderRepository.save(order);
        orderRepository.flush();

        for (Cart cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());

            orderItem.setPrice(cartItem.getProduct().getPrice());

            totalPrice += cartItem.getProduct().getPrice() * cartItem.getQuantity();

            orderItem.setOrder(order);
            orderItems.add(orderItem);

            orderItemRepository.save(orderItem);
        }

        order.setTotalPrice(totalPrice);

        order.setOrderItems(orderItems);
        orderRepository.save(order);
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
