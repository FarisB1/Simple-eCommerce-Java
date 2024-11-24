package com.ahmedfaris.demo.Repositories;

import com.ahmedfaris.demo.Models.Order;
import com.ahmedfaris.demo.Models.OrderItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrder(Order order);

    @Transactional
    void deleteByOrderId(Long orderId);

}
