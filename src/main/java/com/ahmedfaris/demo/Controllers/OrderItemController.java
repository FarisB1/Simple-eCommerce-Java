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

    @GetMapping("/orders/{orderId}")
    public String getOrderItems(@PathVariable Long orderId, Model model) {

        Order order = orderService.getOrderById(orderId);

        List<OrderItem> orderItems = orderItemService.getOrderItemsByOrder(order);

        model.addAttribute("order", order);
        model.addAttribute("orderItems", orderItems);

        return "order_items";
    }
}
