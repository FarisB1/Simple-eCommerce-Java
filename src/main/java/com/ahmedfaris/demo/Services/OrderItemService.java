package com.ahmedfaris.demo.Services;

import com.ahmedfaris.demo.Models.Order;
import com.ahmedfaris.demo.Models.OrderItem;
import com.ahmedfaris.demo.Models.Product;
import com.ahmedfaris.demo.Repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public OrderItem createOrderItem(Order order, Product product, int quantity) {
        if (product == null || order == null) {
            throw new IllegalArgumentException("Product or Order cannot be null");
        }

        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order); // Set the order reference
        orderItem.setProduct(product); // Set the product reference
        orderItem.setProductId(product.getId()); // Set productId
        orderItem.setPrice(product.getPrice()); // Set the price from the product
        orderItem.setQuantity(quantity); // Set the quantity from the cart

        // Save and return the created order item
        return orderItemRepository.save(orderItem);
    }

    public List<OrderItem> getOrderItemsByOrder(Order order) {
        return orderItemRepository.findByOrder(order);}
}
