package com.ahmedfaris.demo.Controllers;

import com.ahmedfaris.demo.Models.Order;
import com.ahmedfaris.demo.Models.OrderItem;
import com.ahmedfaris.demo.Services.OrderItemService;
import com.ahmedfaris.demo.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OrderItemController {

    @Autowired
    private final OrderItemService orderItemService;

    @Autowired
    private final OrderService orderService;

    @Autowired
    public OrderItemController(OrderItemService orderItemService, OrderService orderService) {
        this.orderItemService = orderItemService;
        this.orderService = orderService;
    }

    // New page for displaying order items
    @GetMapping("/orders/{orderId}")
    public String getOrderItems(@PathVariable Long orderId, Model model) {
        // Fetch the order by its ID
        Order order = orderService.getOrderById(orderId);

        // Fetch the order items for the order
        List<OrderItem> orderItems = orderItemService.getOrderItemsByOrder(order);

        // Add order and order items to the model
        model.addAttribute("order", order);
        model.addAttribute("orderItems", orderItems);

        return "order_items"; // Thymeleaf view for displaying order items
    }
}
